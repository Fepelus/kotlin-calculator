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

package com.fepelus.calculator.cli

interface Lexeme {
    fun value(): String
    fun type(): LexType
}

class StringLexeme(val lexemeString: String): Lexeme {
    override fun value(): String = lexemeString
    override fun type(): LexType = LexType.UNKNOWN
}

class NumberLexeme(val innerLexeme: Lexeme): Lexeme {
    override fun value(): String = innerLexeme.value()
    override fun type(): LexType {
        if (innerLexeme.value().matches(Regex("^([0-9]*\\.)?[0-9]+$"))) {
            return LexType.NUMBER
        }
        return innerLexeme.type()
    }
}

class OperatorLexeme(val innerLexeme: Lexeme): Lexeme {
    override fun value(): String = innerLexeme.value()
    override fun type(): LexType {
        if (innerLexeme.value().equals("+")) {
            return LexType.ADD
        }
        if (innerLexeme.value().equals("-")) {
            return LexType.SUBTRACT
        }
        if (innerLexeme.value().equals("*")) {
            return LexType.MULTIPLY
        }
        if (innerLexeme.value().equals("/")) {
            return LexType.DIVIDE
        }
        if (innerLexeme.value().equals("^")) {
            return LexType.POWER
        }
        return innerLexeme.type()
    }
}

class WordLexeme(val innerLexeme: Lexeme): Lexeme {
    override fun value(): String = innerLexeme.value()
    override fun type(): LexType {
        if (innerLexeme.value().equals("pow")) {
            return LexType.POWER
        }
        if (innerLexeme.value().equals("swap")) {
            return LexType.SWAP
        }
        return innerLexeme.type()
    }
}




enum class LexType {
    NUMBER, UNKNOWN, ADD, SUBTRACT, MULTIPLY, DIVIDE, POWER, SWAP
}
