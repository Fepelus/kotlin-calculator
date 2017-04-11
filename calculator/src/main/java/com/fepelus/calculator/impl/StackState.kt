/*
RPC Calculator library
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

package com.fepelus.calculator.impl

import com.fepelus.calculator.OutputBoundary
import com.fepelus.calculator.StackException

class StackState(val model: Model, val outputBoundary: OutputBoundary) : State {
    override fun update() = outputBoundary.update(model.stack, model.sandpit, false)
    override fun digit(i: Int): State {
        val newState = SandpitState(model, outputBoundary)
        return newState.digit(i)
    }

    override fun decimalPoint(): State {
        val newState = SandpitState(Model(model.stack, "0"), outputBoundary)
        return newState.decimalPoint()
    }

    override fun enter(): State = this
    override fun number(d: Double): State {
        val stack = Stack(model.stack)
        stack.push(d)
        return StackState(Model(stack, ""), outputBoundary)
    }

    override fun add(): State {
        if (model.stack.count() == 0) {
            throw StackException("Cannot add when the stack has no elements")
        }

        if (model.stack.count() == 1) {
            throw StackException("Cannot add when the stack has only one element")
        }

        val stack = Stack(model.stack)

        val first = stack.pop()!!
        val second = stack.pop()!!
        stack.push(first + second)

        return StackState(Model(stack, ""), outputBoundary)
    }

    override fun multiply(): State {
        if (model.stack.count() == 0) {
            throw StackException("Cannot multiply when the stack has no elements")
        }

        if (model.stack.count() == 1) {
            throw StackException("Cannot multiply when the stack has only one element")
        }


        val stack = Stack(model.stack)

        val first = stack.pop()!!
        val second = stack.pop()!!
        stack.push(first * second)

        return StackState(Model(stack, ""), outputBoundary)
    }

    override fun subtract(): State {
        if (model.stack.count() == 0) {
            throw StackException("Cannot subtract when the stack has no elements")
        }

        if (model.stack.count() == 1) {
            throw StackException("Cannot subtract when the stack has only one element")
        }

        val stack = Stack(model.stack)

        val first = stack.pop()!!
        val second = stack.pop()!!
        stack.push(second - first)

        return StackState(Model(stack, ""), outputBoundary)
    }

    override fun divide(): State {
        if (model.stack.count() == 0) {
            throw StackException("Cannot divide when the stack has no elements")
        }

        if (model.stack.count() == 1) {
            throw StackException("Cannot divide when the stack has only one element")
        }

        val stack = Stack(model.stack)

        val first = stack.pop()!!
        val second = stack.pop()!!
        stack.push(second / first)

        return StackState(Model(stack, ""), outputBoundary)
    }

    override fun sign(): State {
        if (model.stack.count() < 1) {
            return this
        }

        val stack = Stack(model.stack)
        stack.push(-1.0 * stack.pop())

        return StackState(Model(stack, ""), outputBoundary)
    }

    override fun power(): State {
        if (model.stack.count() == 0) {
            throw StackException("Cannot take the power when the stack has no elements")
        }

        if (model.stack.count() == 1) {
            throw StackException("Cannot take the power when the stack has only one element")
        }

        val stack = Stack(model.stack)

        val first = stack.pop()!!
        val second = stack.pop()!!
        stack.push(Math.pow(second, first))

        return StackState(Model(stack, ""), outputBoundary)
    }

    override fun swap(): State {
        if (model.stack.count() < 2) {
            return this
        }

        val stack = Stack(model.stack)

        val first = stack.pop()!!
        val second = stack.pop()!!
        stack.push(first)
        stack.push(second)
        return StackState(Model(stack, ""), outputBoundary)
    }

    override fun delete(): State {
        if (model.stack.size < 1) {
            return this
        }
        val stack = Stack(model.stack)
        stack.pop()
        return StackState(Model(stack, ""), outputBoundary)
    }
}
