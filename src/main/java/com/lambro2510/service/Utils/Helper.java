package com.lambro2510.service.Utils;

import java.util.ArrayList;
import java.util.List;

public class Helper {
  public static boolean isMaxValueDoubled(double[] outcomes) {
    if (outcomes == null || outcomes.length == 0) {
      return false;
    }

    double max = Double.MIN_VALUE;

    for (double value : outcomes) {
      if (value > max) {
        max = value;
      }
    }

    for (double value : outcomes) {
      if (value != max && value * 2 <= max) {
        return false;
      }
    }

    return true;
  }

  public static List<String> splitTextIntoSentences(String text) {
    List<String> sentences = new ArrayList<>();
    String[] sentenceTokens = text.split("[.,]");
    for (String sentence : sentenceTokens) {
      String trimmedSentence = sentence.trim();
      if (!trimmedSentence.isEmpty()) {
        sentences.add(trimmedSentence);
      }
    }
    return sentences;
  }
  
}
