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
import org.junit.Test

import org.junit.Assert.assertEquals

class RunnerTest {
    @Test
    fun shouldCallCalculator() {
        val lexeme: Lexeme = object: Lexeme {
            override fun value(): String = "1.2"
            override fun type(): LexType = LexType.NUMBER
        }

        val dummy: DummyCalculator = DummyCalculator()
        val lexemeCalculator: LexemeCalculator = LexemeCalculator(dummy)
        lexemeCalculator.dispatch(lexeme)
        assertEquals("number:1.2", dummy.wasSentA)
    }

    @Test
    fun shouldCallCalculatorWithOperators() {
        val dummy: DummyCalculator = DummyCalculator()
        val lexemeCalculator: LexemeCalculator = LexemeCalculator(dummy)
        lexemeCalculator.dispatch(object: Lexeme {
            override fun value(): String = ""
            override fun type(): LexType = LexType.ADD
        })
        assertEquals("add", dummy.wasSentA)
        lexemeCalculator.dispatch(object: Lexeme {
            override fun value(): String = ""
            override fun type(): LexType = LexType.SUBTRACT
        })
        assertEquals("subtract", dummy.wasSentA)
        lexemeCalculator.dispatch(object: Lexeme {
            override fun value(): String = ""
            override fun type(): LexType = LexType.MULTIPLY
        })
        assertEquals("multiply", dummy.wasSentA)
        lexemeCalculator.dispatch(object: Lexeme {
            override fun value(): String = ""
            override fun type(): LexType = LexType.DIVIDE
        })
        assertEquals("divide", dummy.wasSentA)
    }


    @Test
    fun shouldCallCalculatorWithCommands() {
        val dummy: DummyCalculator = DummyCalculator()
        val lexemeCalculator: LexemeCalculator = LexemeCalculator(dummy)
        lexemeCalculator.dispatch(object: Lexeme {
            override fun value(): String = ""
            override fun type(): LexType = LexType.POWER
        })
        assertEquals("power", dummy.wasSentA)
        lexemeCalculator.dispatch(object: Lexeme {
            override fun value(): String = ""
            override fun type(): LexType = LexType.SWAP
        })
        assertEquals("swap", dummy.wasSentA)
    }
}


class DummyCalculator: Calculator {
    var wasSentA: String = "Nothing"
    override fun number(i: Double) { wasSentA = "number:"+i }
    override fun digit(i: Int) { wasSentA = "digit:"+i }
    override fun decimalPoint() { wasSentA = "decimal point" }
    override fun enter() { wasSentA = "enter" }
    override fun add() { wasSentA = "add" }
    override fun multiply() { wasSentA = "multiply" }
    override fun subtract() { wasSentA = "subtract" }
    override fun divide() { wasSentA = "divide" }
    override fun sign() { wasSentA = "sign" }
    override fun swap() { wasSentA = "swap" }
    override fun undo() { wasSentA = "undo" }
    override fun redo() { wasSentA = "redo" }
    override fun power() { wasSentA = "power" }
    override fun delete() { wasSentA = "delete" }
}
