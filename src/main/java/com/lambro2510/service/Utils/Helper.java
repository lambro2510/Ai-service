package com.lambro2510.service.Utils;

import java.util.ArrayList;
import java.util.List;

public class Helper {
  public static boolean isMaxValueOutcome(double[] outcomes, Double percent) {
    percent = 1 / percent;
    if (outcomes == null || outcomes.length == 0) {
      return false;
    }

    for (int value = 0 ; value < outcomes.length; value ++) {
      if(value == 0 || value == 1 || value == 2){
        if(outcomes[value] > outcomes[3] * percent || outcomes[value] > outcomes[4] * percent || outcomes[value] > outcomes[5] * percent || outcomes[value] > outcomes[6] * percent){
          return true;
        }
      }

      if(value == 3 || value == 4){
        if(outcomes[value] > outcomes[0] * percent || outcomes[value] > outcomes[1] * percent || outcomes[value] > outcomes[2] * percent || outcomes[value] > outcomes[5] * percent || outcomes[value] > outcomes[6] * percent){
          return true;
        }
      }

      if(value == 5 || value == 6){
        if(outcomes[value] > outcomes[0] * percent || outcomes[value] > outcomes[1] * percent || outcomes[value] > outcomes[2] * percent || outcomes[value] > outcomes[3] * percent || outcomes[value] > outcomes[4] * percent){
          return true;
        }
      }
    }

    return false;
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
