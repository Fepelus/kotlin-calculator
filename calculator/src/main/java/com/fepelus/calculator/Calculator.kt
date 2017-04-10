package com.fepelus.calculator

interface Calculator {
    fun number(i: Double)
    fun digit(i: Int)
    fun decimalPoint()
    fun enter()
    fun add()
    fun multiply()
    fun subtract()
    fun divide()
    fun sign()
    fun swap()
    fun undo()
    fun redo()
    fun power()
    fun delete()
}