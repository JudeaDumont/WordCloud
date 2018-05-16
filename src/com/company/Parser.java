package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Parser {
    public ArrayList<String> wordArray = new ArrayList<>();
    public HashMap<String, Integer> wordCounts = new HashMap<String, Integer>();
    public HashMap<String, ArrayList<String>> countToWords = new HashMap<String, ArrayList<String>>();

    public Parser(String toParse) {
        initialize(toParse);
    }

    public Parser(File toParse) {
//        String fileName = "C:\\Users\\LABADMIN\\Desktop";

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(toParse.toPath())) {

            stream.forEach(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    initialize(s);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialize(String toParse) {
        String[] split = toParse.split(
                "[ ,.?/;:\"\'\\[\\]\\{\\}\\-=_+\\)\\(!]"
        );
//        System.out.println(split.toString());
        for (String s : split) {
//            System.out.println(s);
            if (
                    s.equals("") ||
                            s.equals("is") ||
                            s.equals("a") ||
                            s.equals("and") ||
                            s.equals("the") ||
                            s.equals("an") ||
                            s.equals("it") ||
                            s.equals("us") ||
                            s.equals("them")) {

            } else {
                addWord(s);
            }
        }
        for (String s : wordArray) {
            String key = wordCounts.get(s).toString();
            if (!countToWords.containsKey(key)) {
                countToWords.put(key, new ArrayList<>());
            }
            countToWords.get(key).add(s);
        }
    }

    public ArrayList<String> alphabeticalMatchSort() {
        Collections.sort(wordArray);
        return wordArray;
    }

    public ArrayList<String> alphabeticalMatchSort(String prefix) {
        ArrayList<String> prefixedSortedWords = new ArrayList<>();
        for (String s : wordArray) {
            if (s.startsWith(prefix)) {
                prefixedSortedWords.add(s);
            }
        }
        Collections.sort(prefixedSortedWords);
        return prefixedSortedWords;
    }

    private void addWord(String s) {
        if (wordCounts.containsKey(s)) {
            Integer count = (wordCounts.get(s));
            wordCounts.put(s, ++count);
        } else {
            wordCounts.put(s, 1);
            wordArray.add(s);
        }
    }


    public HashMap<String, ArrayList<String>> wordsBetweenTwoCounts(int countMin, int countMax) {
        HashMap<String, ArrayList<String>> wordsWithinOccurrenceRange = new HashMap<>();
        for (int i = countMin; i <= countMax; i++) {
            String integerKey = new Integer(i).toString();
            if (countToWords.containsKey(integerKey)) {
                wordsWithinOccurrenceRange.put(integerKey, countToWords.get(integerKey));
            }
        }
        return wordsWithinOccurrenceRange;
    }


    @Override
    public String toString() {
        String aggregateString = "";
        for (String s : wordArray) {
            aggregateString += s + "\n";
        }
        return aggregateString
                + countToWords.toString()
                + wordCounts.toString();
    }
}

