package com.fepelus.calculator.impl

class Model(val stack: List<Double>, val sandpit: String) {
    fun addToSandpit(i: Int): Model {
        return Model(stack, sandpit + i.toString())
    }
}