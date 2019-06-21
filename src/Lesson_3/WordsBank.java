package Lesson_3;

import java.util.HashMap;
import java.util.Map;

public class WordsBank {
    private static String[] wordsArr = {"although", "it", "is", "based", "on", "C++", "Java", "is", "more", "of", "a",
            "pure", "object-oriented", "language", "both", "C++", "and", "Java", "are", "hybrid", "languages", "but",
            "in", "Java", "the", "designers", "felt", "that", "the", "hybridization", "was", "not", "as", "important",
            "as", "it", "was", "in", "Java"};

    public static void main(String[] args) {
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : wordsArr) {
            Integer currentNum = wordsMap.get(word);
            wordsMap.put(word, currentNum == null ? 1 : currentNum + 1);
        }
        System.out.println(wordsMap);
    }
}
