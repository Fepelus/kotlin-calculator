package com.fepelus.calculator.impl

import com.fepelus.calculator.Calculator
import com.fepelus.calculator.OutputBoundary

class CalculatorUseCase(val outputBoundary: OutputBoundary) : Calculator {
    val undoStack: MutableList<State> = mutableListOf()
    val redoStack: MutableList<State> = mutableListOf()
    var state: State = StackState(Model(listOf(), ""), outputBoundary)

    init {
        state.update()
    }

    fun newState(newstate: State) {
        if (state == newstate) {
            return
        }
        undoStack.add(0, state)
        redoStack.clear()
        state = newstate
        state.update()
    }

    override fun undo() {
        redoStack.add(0, state)
        state = undoStack.removeAt(0)
        state.update()
    }

    override fun redo() {
        undoStack.add(0, state)
        state = redoStack.removeAt(0)
        state.update()
    }

    override fun number(i: Double) = newState(state.number(i))
    override fun digit(i: Int) = newState(state.digit(i))
    override fun decimalPoint() = newState(state.decimalPoint())
    override fun enter() = newState(state.enter())
    override fun add() = newState(state.add())
    override fun multiply() = newState(state.multiply())
    override fun subtract() = newState(state.subtract())
    override fun divide() = newState(state.divide())
    override fun sign() = newState(state.sign())
    override fun swap() = newState(state.swap())
    override fun power() = newState(state.power())
    override fun delete() = newState(state.delete())

}


