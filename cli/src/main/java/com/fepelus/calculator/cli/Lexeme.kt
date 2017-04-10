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
        return innerLexeme.type()
    }
}

class WordLexeme(val innerLexeme: Lexeme): Lexeme {
    override fun value(): String = innerLexeme.value()
    override fun type(): LexType {
        if (innerLexeme.value().equals("pow")) {
            return LexType.POWER
        }
        if (innerLexeme.value().equals("^")) {
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