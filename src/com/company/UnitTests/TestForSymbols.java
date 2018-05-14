package com.company.UnitTests;

import com.company.Parser;

public class TestForSymbols {

    public static void main(String[] args) {
	// write your code here

        Parser parser = new Parser("{hi)(, ?my, \"\'name , !is } slim shady");
        System.out.println(parser.toString());

    }
}
