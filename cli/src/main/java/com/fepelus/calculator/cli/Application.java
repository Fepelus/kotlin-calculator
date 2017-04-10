package com.fepelus.calculator.cli;

import com.fepelus.calculator.Calculator;
import com.fepelus.calculator.impl.CalculatorUseCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) throws IOException {
        Lexer lexer = new ParsedString(getStdin());
        BufferedBoundary boundary = new BufferedBoundary();
        Calculator calc = new CalculatorUseCase(boundary);
        LexemeCalculator join = new LexemeCalculator(calc);
        for (Lexeme lexeme: lexer.lexemes()) {
            join.dispatch(lexeme);
        }
        System.out.println(boundary.lastUpdate().toString());
    }

    private static String getStdin() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        StringBuffer output = new StringBuffer();
        while ((s = in.readLine()) != null && s.length() != 0)
            output.append(s);
        return output.toString();
    }
}
