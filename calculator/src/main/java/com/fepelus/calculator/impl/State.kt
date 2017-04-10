package com.fepelus.calculator.impl

interface State {
    fun update()
    fun digit(i: Int): State
    fun decimalPoint(): State
    fun enter(): State
    fun number(i: Double): State
    fun add(): State
    fun multiply(): State
    fun subtract(): State
    fun divide(): State
    fun sign(): State
    fun swap(): State
    fun power(): State
    fun delete(): State
}