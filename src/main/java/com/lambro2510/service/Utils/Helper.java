package com.lambro2510.service.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {
  public static boolean isMaxValueOutcome(double[] outcomes, Double percent) {
    if (outcomes == null || outcomes.length == 0) {
      return false;
    }

    double max = outcomes[0];
    for (double i : outcomes){
      if (i > max){
        max = i;
      }
    }

    for (int value = 0; value < outcomes.length; value++) {
      if (value == 0 || value == 1) {
        if (max * percent < outcomes[2] || max * percent < outcomes[3] || max * percent < outcomes[4]) {
          return true;
        }
      }

      if (value == 2) {
        if (max * percent < outcomes[0] || max * percent < outcomes[1] || max * percent < outcomes[3] || max * percent < outcomes[4]) {
          return true;
        }
      }

      if (value == 3 || value == 4) {
        if (max * percent < outcomes[0] || max * percent < outcomes[1] || max * percent < outcomes[2] || max * percent < outcomes[3]) {
          return true;
        }
      }
    }

    return false;
  }

  public static List<String> splitTextIntoSentences(String text) {
    List<String> sentences = new ArrayList<>();
    String[] sentenceTokens = text.split("[.,!\n;?]");
    for (String sentence : sentenceTokens) {
      String trimmedSentence = sentence.trim();
      if (!trimmedSentence.isEmpty()) {
        sentences.add(trimmedSentence);
      }
    }
    return sentences;
  }

}
