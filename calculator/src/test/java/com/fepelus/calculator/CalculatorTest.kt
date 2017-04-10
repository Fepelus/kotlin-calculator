package com.fepelus.calculator;


import com.fepelus.calculator.impl.CalculatorUseCase
import io.kotlintest.matchers.fail
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec


class CalculatorTest : ShouldSpec() {
    init {
        "Calculator" {
            should("have the correct initial state") {
                // Given a new calculator
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)

                // When you have not done anything

                // Then it should update the observer with the following initial state
                isNotNull(obs.stack)
                obs.stack?.size shouldBe 0
                obs.sandpit shouldBe ""
                obs.inSandpit shouldBe false
                obs.updateCount shouldBe 1
            }

            should("accept a single digit") {
                // Given a new calculator
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)

                // When I send the calculator a digit
                calc.digit(9)

                // Then the calculator should be inSandpit
                obs.inSandpit shouldBe true

                // And the stack is still empty
                obs.stack?.size shouldBe 0

                // And the sandpit should have the digit
                obs.sandpit shouldBe "9"

                // And the observer has been updated
                obs.updateCount shouldBe 2
            }

            should("accept a second digit") {
                // Given a new calculator
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)

                // When I send the calculator two digits
                calc.digit(4)
                calc.digit(2)

                // Then the calculator should be inSandpit
                obs.inSandpit shouldBe true

                // And the stack is still empty
                obs.stack?.size shouldBe 0

                // And the sandpit should have the two digits
                obs.sandpit shouldBe "42"

                // And the observer has been updated
                obs.updateCount shouldBe 3
            }

            should("enter digits on to the stack") {
                // Given a calculator with two digits in the sandpit
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)

                // When I enter the digit onto the stack
                calc.enter()

                // Then the head of the stack is my number
                obs.stack?.get(0) shouldBe 42.0

                // And the stack is not empty
                obs.stack?.size shouldBe 1

                // And the calculator should not be inSandpit
                obs.inSandpit shouldBe false

                // And, thus, the sandpit should be empty
                obs.sandpit shouldBe ""
            }


            should("enter decimal points") {
                // Given a calculator with two digits in the sandpit
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)

                // When I send the calculator a decimal point
                calc.decimalPoint()

                // Then the calculator should be inSandpit
                obs.inSandpit shouldBe true

                // And the stack is still empty
                obs.stack?.size shouldBe 0

                // And the sandpit should have the two digits
                obs.sandpit shouldBe "42."

                // And the observer has been updated
                obs.updateCount shouldBe 4
            }

            should("enter more digits after the decimal point") {
                // Given a calculator with digits and a decimal point in the sandpit
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)
                calc.decimalPoint()

                // When I send the calculator more digits
                calc.digit(1)
                calc.digit(3)

                // Then the calculator should be inSandpit
                obs.inSandpit shouldBe true

                // And the stack is still empty
                obs.stack?.size shouldBe 0

                // And the sandpit should have the two digits
                obs.sandpit shouldBe "42.13"

                // And the observer has been updated
                obs.updateCount shouldBe 6
            }

            should("not enter more decimal points after the first one") {
                // Given a calculator with digits and a decimal point in the sandpit
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)
                calc.decimalPoint()

                // When I send the calculator more decimal points
                calc.decimalPoint()
                calc.decimalPoint()

                // Then the sandpit should have only the one decimal point
                obs.sandpit shouldBe "42."

                // And the observer has not been updated more times
                obs.updateCount shouldBe 4
                // And the calculator should be inSandpit
                obs.inSandpit shouldBe true

                // And the stack is still empty
                obs.stack?.size shouldBe 0
            }

            should("add complete numbers onto the stack") {
                // Given a new calculator
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)

                // When I send the calculator a number
                calc.number(12.34)

                // Then the head of the stack is my number
                obs.stack?.get(0) shouldBe 12.34

                // And the stack is not empty
                obs.stack?.size shouldBe 1

                // And the calculator should not be inSandpit
                obs.inSandpit shouldBe false

                // And, thus, the sandpit should be empty
                obs.sandpit shouldBe ""

            }


            should("add two numbers") {
                // Given a calculator with two numbers on the stack
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)
                calc.enter()
                calc.digit(9)
                calc.digit(9)
                calc.enter()

                // When I add the two numbers
                calc.add()

                // Then the head of the stack is my number
                obs.stack?.get(0) shouldBe 141.0

                // And the stack is not empty
                obs.stack?.size shouldBe 1

                // And the calculator should not be inSandpit
                obs.inSandpit shouldBe false

                // And, thus, the sandpit should be empty
                obs.sandpit shouldBe ""
            }


            should("add two numbers with three numbers on the stack") {
                // Given a calculator with two numbers on the stack and a third in the sandpit
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)
                calc.enter()
                calc.digit(9)
                calc.digit(9)
                calc.enter()
                calc.digit(7)
                calc.digit(3)

                // When I add the two numbers
                calc.add()

                // Then the head of the stack is my number
                obs.stack?.get(0) shouldBe 172.0

                // And the stack is not empty
                obs.stack?.size shouldBe 2

                // And the calculator should not be inSandpit
                obs.inSandpit shouldBe false

                // And, thus, the sandpit should be empty
                obs.sandpit shouldBe ""
            }


            should("multiply two numbers") {
                // Given a calculator with two numbers
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)
                calc.enter()
                calc.digit(9)
                calc.digit(9)

                // When I multiply the two numbers
                calc.multiply()

                // Then the head of the stack is my number
                obs.stack?.get(0) shouldBe 4158.0

                // And the stack is not empty
                obs.stack?.size shouldBe 1

                // And the calculator should not be inSandpit
                obs.inSandpit shouldBe false

                // And, thus, the sandpit should be empty
                obs.sandpit shouldBe ""
            }

            should("subtract two numbers") {
                // Given a calculator with two numbers
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)
                calc.enter()
                calc.digit(9)
                calc.digit(9)

                // When I subtract the two numbers
                calc.subtract()

                // Then the head of the stack is my number
                obs.stack?.get(0) shouldBe -57.0

                // And the stack is not empty
                obs.stack?.size shouldBe 1

                // And the calculator should not be inSandpit
                obs.inSandpit shouldBe false

                // And, thus, the sandpit should be empty
                obs.sandpit shouldBe ""
            }

            should("divide two numbers") {
                // Given a calculator with two numbers
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)
                calc.enter()
                calc.digit(2)

                // When I subtract the two numbers
                calc.divide()

                // Then the head of the stack is my number
                obs.stack?.get(0) shouldBe 21.0

                // And the stack is not empty
                obs.stack?.size shouldBe 1

                // And the calculator should not be inSandpit
                obs.inSandpit shouldBe false
            }

            should("swap the top two numbers on the stack") {
                // Given a calculator with two numbers
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)
                calc.enter()
                calc.digit(2)
                calc.enter()

                // When I subtract the two numbers
                calc.swap()

                // Then what was the head of the stack is now second from the top
                obs.stack?.get(1) shouldBe 2.0

                // And what was second from the top is now the head of the stack
                obs.stack?.get(0) shouldBe 42.0

                // And the stack has not changed size
                obs.stack?.size shouldBe 2

                // And the calculator should not be inSandpit
                obs.inSandpit shouldBe false
            }


            should("swap the sandpit with the top of the stack") {
                // Given a calculator with two numbers
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)
                calc.enter()
                calc.digit(2)

                // When I subtract the two numbers
                calc.swap()

                // Then what was the head of the stack is now second from the top
                obs.stack?.get(1) shouldBe 2.0

                // And what was second from the top is now the head of the stack
                obs.stack?.get(0) shouldBe 42.0

                // And the stack has not changed size
                obs.stack?.size shouldBe 2

                // And the calculator should not be inSandpit
                obs.inSandpit shouldBe false
            }

            should("find the power of a number") {
                // Given a calculator with two numbers
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)
                calc.enter()
                calc.digit(2)

                // When I find the power of the first number to the second
                calc.power()

                // Then the result is pushed to the stack
                obs.stack?.get(0) shouldBe 1764.0

                // And the stack has the one value
                obs.stack?.size shouldBe 1
            }

            should("undo") {
                // Given a calculator with a whole bunch of stuff done to it
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(2)
                calc.enter()
                calc.digit(4)
                calc.digit(2)
                calc.swap()
                calc.divide()
                calc.digit(2)
                calc.decimalPoint()
                calc.digit(5)
                calc.multiply()

                // When I 'undo' the last three operations
                calc.undo()
                calc.undo()
                calc.undo()

                // Then the stack should have had the multiplication reverted
                obs.stack?.get(0) shouldBe 21.0

                // And the sandpit should still have a decimal point
                obs.sandpit shouldBe "2"

                // And the calculator should be inSandpit
                obs.inSandpit shouldBe true
            }

            should("redo") {
                // Given a calculator with a whole bunch of stuff done to it
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(2)
                calc.enter()
                calc.digit(4)
                calc.digit(2)
                calc.swap()
                calc.divide()
                calc.digit(2)
                calc.decimalPoint()
                calc.digit(5)
                calc.multiply()

                // When I 'undo' the last three operations
                calc.undo()
                calc.undo()
                calc.redo()

                // Then the stack should have had the multiplication reverted
                obs.stack?.get(0) shouldBe 21.0

                // And the sandpit should still have a decimal point
                obs.sandpit shouldBe "2.5"

                // And the calculator should be inSandpit
                obs.inSandpit shouldBe true
            }

            should("sign on the stack") {
                // Given a calculator with a number on the stack
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)
                calc.enter()

                // When I reverse the sign
                calc.sign()

                // Then the stack should have reversed the sign of the number
                obs.stack?.get(0) shouldBe -42.0

                // And the calculator should not be inSandpit
                obs.inSandpit shouldBe false

                // When I reverse again
                calc.sign()

                // Then the stack should have reversed the sign of the number again
                obs.stack?.get(0) shouldBe 42.0

            }

            should("sign in the sandpit") {
                // Given a calculator with a number in the sandpit
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)

                // When I reverse the sign
                calc.sign()

                // Then the sandpit has a negative sign
                obs.sandpit shouldBe "-42"
                // And the calculator should still be inSandpit
                obs.inSandpit shouldBe true

                // When I reverse again
                calc.sign()

                // Then the sandpit has no negative sign
                obs.sandpit shouldBe "42"
            }


            should("delete from sandpit") {
                // Given a calculator with two numbers
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)
                calc.enter()
                calc.digit(2)
                calc.digit(1)

                // When I find the power of the first number to the second
                calc.delete()

                // Then the sandpit has had a digit deleted
                obs.sandpit shouldBe "2"

                // And we are still in the sandpit
                obs.inSandpit shouldBe true

                // And the result is pushed to the stack
                obs.stack?.get(0) shouldBe 42.0
            }

            should("delete from stack") {
                // Given a calculator with two numbers
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)
                calc.enter()
                calc.digit(2)
                calc.digit(1)
                calc.enter()

                // When I find the power of the first number to the second
                calc.delete()

                // Then the stack should just have the one value
                obs.stack?.count() shouldBe 1

                // And its value should be 42
                obs.stack?.get(0) shouldBe 42.0

                // And we are not in the sandpit
                obs.inSandpit shouldBe false
            }

            should("delete from stack with nothing on it") {
                // Given a calculator with nothing on it
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)

                // When I find the power of the first number to the second
                calc.delete()

                // Then the stack should just have the one value
                obs.stack?.count() shouldBe 0

                // And we are not in the sandpit
                obs.inSandpit shouldBe false
            }

            should ("do nothing if you swap a stack that is too small") {
                // Given a calculator with only one number on it
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)
                calc.enter()

                // When I swap the stack with only one number on it
                calc.swap()

                // Then nothing should happen
                obs.stack?.count() shouldBe 1
                obs.stack?.get(0) shouldBe 42.0
                obs.inSandpit shouldBe false
            }

            should ("throw an exception when I add with a stack that is too small") {
                // Given a calculator with only one number on it
                val obs: DummyOutputBoundary = DummyOutputBoundary()
                val calc: Calculator = CalculatorUseCase(obs)
                calc.digit(4)
                calc.digit(2)
                calc.enter()

                // When I click add with only one number on it
                try {
                    calc.add()
                    fail("Adding a stack that is too small should throw an exception")
                } catch (e: Exception) {
                    // Then the calculator should throw an exception
                    e.message shouldBe "Cannot add when the stack has only one element"
                }

                // And the state should stay the same
                obs.stack?.count() shouldBe 1
                obs.stack?.get(0) shouldBe 42.0
                obs.inSandpit shouldBe false


                // When I click add with no numbers on it
                calc.delete()
                try {
                    calc.add()
                    fail("Adding a stack that is too small should throw an exception")
                } catch (e: Exception) {
                    // Then the calculator should throw an exception
                    e.message shouldBe "Cannot add when the stack has no elements"
                }

                // And the state should stay the same
                obs.stack?.count() shouldBe 0
                obs.inSandpit shouldBe false
            }
        }
    }

    fun isNotNull(input: Any?) {
        if (input == null) {
            throw AssertionError("object was supposed to be not null but was null")
        }
    }

    fun shouldBeNull(input: Any?) {
        if (input != null) {
            throw AssertionError("object was supposed to be null but was not null")
        }
    }
}


class DummyOutputBoundary : OutputBoundary {

    var stack: List<Double>? = null
    var sandpit: String? = null
    var inSandpit: Boolean? = null
    var updateCount: Int = 0

    override fun update(stack: List<Double>, sandpit: String, inSandpit: Boolean) {
        this.stack = stack
        this.sandpit = sandpit
        this.inSandpit = inSandpit
        updateCount += 1
    }
}

