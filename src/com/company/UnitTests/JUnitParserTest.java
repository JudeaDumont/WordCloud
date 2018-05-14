package com.company.UnitTests;

import com.company.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class JUnitParserTest {

    @Test
    public void alphabeticalMatchSortTest() {
        Parser parser = new Parser("{hi ) (, ?my.my., \"\'name name name , !is } slim slim slim slim shady shady shady shady shady");
        Assertions.assertTrue(parser.alphabeticalMatchSort("s").toString().equals("[shady, slim]"));
    }

    @Test
    public void fileTest() {
        Parser parser = new Parser(new File("TextToParse.txt")); //contained in the root of the project
        Assertions.assertTrue(parser.alphabeticalMatchSort("s").toString().equals("[s, sort, split, startsWith, stream]"));
    }
    @Test
    public void TestForOccurrenceRange() {
        Parser parser = new Parser("{hi ) (, ?my.my., \"\'name name name , !is } slim slim slim slim shady shady shady shady shady");
        Assertions.assertTrue(parser.wordsBetweenTwoCounts(3,4).toString().equals("{3=[name], 4=[slim]}"));
    }

    @Test
    public void TestForSymbols() {
        Parser parser = new Parser("{hi)(, ?my, \"\'name , !is } slim shady");
        Assertions.assertTrue(parser.wordArray.toString().equals("[hi, my, name, slim, shady]"));
    }

    @Test
    public void TestForWordCount() {
        Parser parser = new Parser("{hi ) (, ?my.my., \"\'name name name , !is } slim slim slim slim shady shady shady shady shady");
        Assertions.assertTrue(parser.wordCounts.toString().equals("{hi=1, shady=5, name=3, slim=4, my=2}"));
    }
}
