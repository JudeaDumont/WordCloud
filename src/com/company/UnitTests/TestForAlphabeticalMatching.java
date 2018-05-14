package com.company.UnitTests;

import com.company.Parser;

public class TestForAlphabeticalMatching {

    public static void main(String[] args) {
	// write your code here

        Parser parser = new Parser("{hi ) (, ?my.my., \"\'name name name , !is } slim slim slim slim shady shady shady shady shady");
        System.out.println(parser.alphabeticalMatchSort("s"));
    }
}
