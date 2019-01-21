package com.waes.diff.controllers;

import com.waes.diff.controllers.dtos.GetDiffResponseDto;
import com.waes.diff.controllers.dtos.PutDiffRequestDto;
import com.waes.diff.controllers.util.Converter;
import com.waes.diff.domain.Diff;
import com.waes.diff.services.DiffService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Diff API controller */
@RestController
@RequestMapping(path = DiffController.VERSION + "/diff")
public class DiffController {

  public static final String VERSION = "/v1";

  private DiffService diffService;

  @Autowired
  public DiffController(DiffService diffService) {
    this.diffService = diffService;
  }

  @ApiOperation(value = "Adds JSON text content base64 encoded as the left text to be diffed")
  @PutMapping(path = "/{id}/left")
  public void addLeftText(
      @PathVariable("id") String id, @RequestBody PutDiffRequestDto requestBody) {
    diffService.addLeftText(id, requestBody.getData());
  }

  @ApiOperation(value = "Adds JSON text content base64 encoded as the right text to be diffed")
  @PutMapping(path = "/{id}/right")
  public void addRightText(
      @PathVariable("id") String id, @RequestBody PutDiffRequestDto requestBody) {
    diffService.addRightText(id, requestBody.getData());
  }

  @ApiOperation(value = "Retrieves the result of the diff operation")
  @GetMapping(path = "/{id}")
  public GetDiffResponseDto getDiffResult(@PathVariable("id") String id) {

    Diff diffResult = diffService.getDiffResult(id);
    GetDiffResponseDto responseDto = Converter.convertDiffEntityToDto(diffResult);

    return responseDto;
  }
}
