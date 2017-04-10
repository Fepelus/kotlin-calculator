package com.fepelus.calculator

interface OutputBoundary {
    fun update(stack: List<Double>, sandpit: String, inSandpit: Boolean)
}