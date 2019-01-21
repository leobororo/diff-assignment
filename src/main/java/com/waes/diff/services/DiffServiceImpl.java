package com.waes.diff.services;

import com.waes.diff.domain.Diff;
import com.waes.diff.enums.DiffStatus;
import com.waes.diff.exceptions.DataNotDecodeableException;
import com.waes.diff.exceptions.DiffNotFoundException;
import com.waes.diff.repositories.DiffRepository;
import java.util.Base64;
import java.util.HashSet;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Diff service class */
@Service
@Transactional
@Slf4j
public class DiffServiceImpl implements DiffService {

  private DiffRepository diffRepository;

  @Autowired
  public DiffServiceImpl(DiffRepository diffRepository) {
    this.diffRepository = diffRepository;
  }

  /**
   * Method to update the left text. First, it tries to decode the provided text. Then searches for
   * a diff document with the given id. If the document is found, updates its left text, otherwise a
   * new document will be created with the left text value set. Finally, verifies if the diff is
   * ready for processing and updates its status if necessary.
   *
   * @param id {@link String}
   * @param text {@link String}
   * @return {@link Diff}
   */
  @Override
  public Diff addLeftText(String id, String text) {
    log.info("Adding left text, diff id = " + id);

    decode(text);

    Diff diff = getDiffForAddingText(id);
    diff.setLeft(text);

    verifyIsReadyToBeProcessed(diff, diff.getRight());

    return diffRepository.save(diff);
  }

  /**
   * Method to update the right text. First, it tries to decode the provided text. Then searches for
   * a diff document with the given id. If the document is found, updates its right text, otherwise
   * a new document will be created with the right text value set. Finally, verifies if the diff is
   * ready for processing and updates its status if necessary.
   *
   * @param id {@link String}
   * @param text {@link String}
   * @return {@link Diff}
   */
  @Override
  public Diff addRightText(String id, String text) {
    log.info("Adding right text, diff id = " + id);

    decode(text);

    Diff diff = getDiffForAddingText(id);
    diff.setRight(text);

    verifyIsReadyToBeProcessed(diff, diff.getLeft());

    return diffRepository.save(diff);
  }

  /**
   * Returns a diff with the specified id. If the diff cannot be found it raises a {@link
   * DiffNotFoundException}, otherwise it returns the diff
   *
   * @param id {@link String}
   * @return {@link Diff}
   */
  @Override
  public Diff getDiffResult(String id) {
    log.info("Retrieving diff results, diff id = " + id);

    Optional<Diff> diffOptional = diffRepository.findById(id);

    if (!diffOptional.isPresent()) {
      throw new DiffNotFoundException(id);
    }

    return diffOptional.get();
  }

  /**
   * Searches for the diff document with the given id. If the diff could not be found it returns a
   * new diff with the given id set. The method also sets the diff {@link DiffStatus} to
   * NOT_PROCESSED
   *
   * @param id {@link String}
   * @return
   */
  private Diff getDiffForAddingText(String id) {
    Optional<Diff> diffOptional = diffRepository.findById(id);

    Diff diff;
    if (diffOptional.isPresent()) {
      diff = diffOptional.get();
    } else {
      diff = Diff.builder().id(id).build();
    }

    diff.setStatus(DiffStatus.NOT_PROCESSED);
    diff.setDiffItems(new HashSet<>());

    return diff;
  }

  /**
   * Sets the diff {@link DiffStatus} to READY_TO_PROCESS if the given text is not null or empty
   *
   * @param diff {@link Diff}
   * @param text {@link String}
   */
  private void verifyIsReadyToBeProcessed(Diff diff, String text) {
    if (text != null && !text.isEmpty()) {
      diff.setStatus(DiffStatus.READY_TO_PROCESS);
    }
  }

  /**
   * Tries to decode the text from base64 encoding. It throws an {@link DataNotDecodeableException}
   * if the decoding fails
   *
   * @param text {@link String}
   */
  private void decode(String text) {
    try {
      Base64.getDecoder().decode(text);
    } catch (IllegalArgumentException e) {
      throw new DataNotDecodeableException(text);
    }
  }
}
