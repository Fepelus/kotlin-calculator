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

import com.fepelus.calculator.StackException

class Stack(input: List<Double>) : List<Double> {

    var items: MutableList<Double> = input.toMutableList()

    fun push(element: Double) {
        this.items.add(0, element)
    }

    fun pop(): Double {
        if (this.items.size == 0) {
            throw StackException("Cannot pop a stack with no elements")
        }
        return this.items.removeAt(0)
    }

    override fun isEmpty(): Boolean = this.items.isEmpty()
    override fun contains(element: Double): Boolean = this.items.contains(element)
    override fun containsAll(elements: Collection<Double>): Boolean = containsAll(elements)
    override fun get(index: Int): Double = this.items.get(index)
    override fun indexOf(element: Double): Int = this.items.indexOf(element)
    override fun iterator(): Iterator<Double> = this.items.iterator()
    override fun lastIndexOf(element: Double): Int = this.items.lastIndexOf(element)
    override fun listIterator(): ListIterator<Double> = this.items.listIterator()
    override fun listIterator(index: Int): ListIterator<Double> = this.items.listIterator(index)
    override fun subList(fromIndex: Int, toIndex: Int): List<Double> = this.items.subList(fromIndex, toIndex)
    override val size: Int
        get() = this.items.size

    override fun toString() = this.items.toString()
}