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
