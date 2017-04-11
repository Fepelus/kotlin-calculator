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

import com.fepelus.calculator.Calculator

class LexemeCalculator(val calculator: Calculator) {
    fun  dispatch(lexeme: Lexeme) {
        if (lexeme.type() == LexType.NUMBER) {
            calculator.number(lexeme.value().toDouble())
        } else if (lexeme.type() == LexType.ADD) {
            calculator.add()
        } else if (lexeme.type() == LexType.SUBTRACT) {
            calculator.subtract()
        } else if (lexeme.type() == LexType.MULTIPLY) {
            calculator.multiply()
        } else if (lexeme.type() == LexType.DIVIDE) {
            calculator.divide()
        } else if (lexeme.type() == LexType.POWER) {
            calculator.power()
        } else if (lexeme.type() == LexType.SWAP) {
            calculator.swap()
        } else if (lexeme.type() == LexType.UNKNOWN) {
            throw BadInputException(String.format("There is no operator '%s'", lexeme.value()))
        }
    }
}

class BadInputException(val msg: String) : Throwable(msg) {}
