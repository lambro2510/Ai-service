package com.lambro2510.service.Utils;

import java.util.ArrayList;
import java.util.List;

public class Helper {
  public static boolean isMaxValueDoubled(double[] outcomes, Double percent) {
    if (outcomes == null || outcomes.length == 0) {
      return false;
    }

    double max = Double.NEGATIVE_INFINITY; // Sử dụng Double.NEGATIVE_INFINITY để đảm bảo rằng max có giá trị thấp nhất ban đầu

    for (double value : outcomes) {
      if (value > max) {
        max = value;
      }
    }

    for (double value : outcomes) {
      if (value != max && value <= max * percent) { // Kiểm tra xem giá trị có lớn hơn hoặc bằng 70% giá trị lớn nhất hay không
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
