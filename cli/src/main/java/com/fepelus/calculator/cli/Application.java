/*
RPC Calculator command line client
Copyright (C) 2017 Patrick Borgeest

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
		try {
			for (Lexeme lexeme: lexer.lexemes()) {
				join.dispatch(lexeme);
			}
			System.out.println(boundary.lastUpdate().toString());
		} catch (Throwable e) {
			System.err.print(String.format("%s%nOperators: + - / * ^ pow swap%n", e.getMessage()));
		}
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
