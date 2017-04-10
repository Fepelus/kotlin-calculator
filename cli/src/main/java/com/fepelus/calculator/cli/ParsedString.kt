package com.fepelus.calculator.cli

internal class ParsedString(private val content: String) : Lexer {
    override fun lexemes(): List<Lexeme> {
        return content.split(Regex("[ \t\n]+")).map { strng ->
            WordLexeme(
                OperatorLexeme(
                    NumberLexeme(
                        StringLexeme(strng)
                    )
                )
            )
        }
    }
}

