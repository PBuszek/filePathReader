package main.java.com.codecool.filepartreader;

import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FileWordAnalyzer {
    private final FilePartReader reader;

    FileWordAnalyzer(FilePartReader reader) {
        this.reader = reader;
    }

    /**
     * calls FilePartReader.readLines()
     *
     * @return the words ordered by alphabetically as an ArrayList
     */
    List<String> getWordsOrderedAlphabetically() throws IOException {
        String[] words = getWords();
        Arrays.sort(words, Collator.getInstance());
        return Arrays.asList(words);
    }

    private String[] getWords() throws IOException {
        return reader.readLines().replaceAll("\\p{P}", "").split("\\s+");
    }

    /**
     * calls FilePartReader.readLines()
     *
     * @return the words which contains the subString
     */
    List<String> getWordsContainingSubstring(String subString) throws IOException {
        List<String> wordsContaining = new ArrayList<>();
        for (String word : getWords()) {
            if (word.contains(subString)) {
                wordsContaining.add(word);
            }
        }
        return wordsContaining;
    }

    /**
     * calls FilePartReader.readLines()
     *
     * @return the words from the String which are palindrome
     */
    List<String> getStringsWhichPalindromes() throws IOException {
        List<String> palindromes = new ArrayList<>();
        for (String word : getWords()) {
            if (istPalindrome(word)) {
                palindromes.add(word);
            }
        }
        return palindromes;
    }

    private boolean istPalindrome(String word) {
        var chars = word.toLowerCase().toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (j > i) {
            if (chars[i++] != chars[j--]) {
                return false;
            }
        }
        return true;
    }
}
