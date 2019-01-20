package com.waes.diff_assessment.controllers;

import static com.google.common.collect.Sets.newHashSet;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.waes.diff_assessment.controllers.dtos.GetDiffResponseDto;
import com.waes.diff_assessment.controllers.dtos.PutDiffRequestDto;
import com.waes.diff_assessment.controllers.util.Converter;
import com.waes.diff_assessment.domain.Diff;
import com.waes.diff_assessment.domain.DiffItem;
import com.waes.diff_assessment.enums.DiffStatus;
import com.waes.diff_assessment.repositories.DiffRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DiffControllerIntegrationTest {

  public static final String ID = "1000";
  public static final String RESOURCE_PATH = "/v1/diff/";
  public static final String SAMPLE_BASE_64_TEXT = "a2FwcHkgTnJyclllYXIgbXkgZnJpZW5hYQ==";
  public static final String BAD_SAMPLE_BASE_64_TEXT = "a2FwcHkgTnJyclllYXIgbXkgZnJpZW5hYQ=A";

  @Autowired private MockMvc mvc;

  @Autowired private DiffRepository repository;

  @Test
  public void addLeftTextShouldUpdateLeftText() throws Exception {
    PutDiffRequestDto dto = PutDiffRequestDto.builder().data(SAMPLE_BASE_64_TEXT).build();

    mvc.perform(
            put(RESOURCE_PATH + ID + "/left")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
        .andExpect(MockMvcResultMatchers.status().isOk());

    assertThat(repository.findById(ID).isPresent(), equalTo(true));
    assertThat(repository.findById(ID).get().getLeft(), equalTo(SAMPLE_BASE_64_TEXT));
  }

  @Test
  public void addLeftTextShouldNotUpdateIfTextNotDecodeable() throws Exception {
    PutDiffRequestDto dto = PutDiffRequestDto.builder().data(BAD_SAMPLE_BASE_64_TEXT).build();

    mvc.perform(
            put(RESOURCE_PATH + ID + "/left")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
  }

  @Test
  public void addRightTextShouldUpdateRightText() throws Exception {
    PutDiffRequestDto dto = PutDiffRequestDto.builder().data(SAMPLE_BASE_64_TEXT).build();

    mvc.perform(
            put(RESOURCE_PATH + ID + "/right")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
        .andExpect(MockMvcResultMatchers.status().isOk());

    assertThat(repository.findById(ID).isPresent(), equalTo(true));
    assertThat(repository.findById(ID).get().getRight(), equalTo(SAMPLE_BASE_64_TEXT));
  }

  @Test
  public void addRightTextShouldNotUpdateIfTextNotDecodeable() throws Exception {
    PutDiffRequestDto dto = PutDiffRequestDto.builder().data(BAD_SAMPLE_BASE_64_TEXT).build();

    mvc.perform(
            put(RESOURCE_PATH + ID + "/right")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
  }

  @Test
  public void getDiffResultShouldReturnDiffResults() throws Exception {
    Diff diff = createDiff();
    repository.save(diff);

    GetDiffResponseDto diffResponseDto = Converter.convertDiffEntityToDto(diff);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json = ow.writeValueAsString(diffResponseDto.getDiffs());

    mvc.perform(get(RESOURCE_PATH + ID).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("$.status").value(DiffStatus.NOT_EQUALS.toString()))
        .andExpect(jsonPath("$.diffs[0].offset").value("0"))
        .andExpect(jsonPath("$.diffs[0].length").value("1"))
        .andExpect(jsonPath("$.diffs[1].offset").value("20"))
        .andExpect(jsonPath("$.diffs[1].length").value("10"));
  }

  @Test
  public void getDiffResultShouldNotReturnDiffResultsIfDiffNotExistent() throws Exception {
    mvc.perform(get(RESOURCE_PATH + ID + 1).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  private Diff createDiff() {
    DiffItem diffItem1 = createDiffItem(0, 1);
    DiffItem diffItem2 = createDiffItem(20, 10);

    Diff diff =
        Diff.builder()
            .id(ID)
            .status(DiffStatus.NOT_EQUALS)
            .left(SAMPLE_BASE_64_TEXT)
            .right(SAMPLE_BASE_64_TEXT)
            .diffItems(newHashSet(diffItem1, diffItem2))
            .build();

    return diff;
  }

  private DiffItem createDiffItem(int offset, int length) {
    return DiffItem.builder().length(length).offset(offset).build();
  }
}
