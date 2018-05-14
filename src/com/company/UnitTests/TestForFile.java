package com.company.UnitTests;

import com.company.Parser;

import java.io.File;

public class TestForFile {

    public static void main(String[] args) {
	// write your code here

        Parser parser = new Parser(new File("TextToParse.txt")); //contained in the root of the project
        System.out.println(parser.alphabeticalMatchSort("s"));
    }
}
