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

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LexerTest {
    @Test
    public void shouldSplitBySpaces() {
        Lexer lexer = new ParsedString("one two");
        List<Lexeme> lexemes = lexer.lexemes();
        assertEquals(2, lexemes.size());
        assertEquals("one", lexemes.get(0).value());
    }

    @Test
    public void shouldDetectNumbers() {
        Lexer lexer = new ParsedString("1 1.2 1.2.3 .4");
        List<Lexeme> lexemes = lexer.lexemes();
        assertEquals(LexType.NUMBER, lexemes.get(0).type());
        assertEquals(LexType.NUMBER, lexemes.get(1).type());
        assertEquals(LexType.UNKNOWN, lexemes.get(2).type());
        assertEquals(LexType.NUMBER, lexemes.get(3).type());
    }

    @Test
    public void shouldDetectTheFourBasicOperations() {
        Lexer lexer = new ParsedString("+ - / *");
        List<Lexeme> lexemes = lexer.lexemes();
        assertEquals(LexType.ADD, lexemes.get(0).type());
        assertEquals(LexType.SUBTRACT, lexemes.get(1).type());
        assertEquals(LexType.DIVIDE, lexemes.get(2).type());
        assertEquals(LexType.MULTIPLY, lexemes.get(3).type());
    }

    @Test
    public void shouldParseWords() {
        Lexer lexer = new ParsedString("pow swap ^");
        List<Lexeme> lexemes = lexer.lexemes();
        assertEquals(LexType.POWER, lexemes.get(0).type());
        assertEquals(LexType.SWAP, lexemes.get(1).type());
        assertEquals(LexType.POWER, lexemes.get(2).type());
    }

}
