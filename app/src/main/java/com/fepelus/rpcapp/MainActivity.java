package com.fepelus.rpcapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fepelus.calculator.Calculator;
import com.fepelus.calculator.OutputBoundary;
import com.fepelus.calculator.impl.CalculatorUseCase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OutputBoundary {

    private Calculator calculator;

    TextView[] stackViews;
    TextView sandpitView;

    Button undoButton;
    Button powerButton;
    Button swapButton;
    Button deleteButton;
    Button redoButton;
    Button reverseSignButton;
    Button divideButton;
    Button multiplyButton;
    Button sevenButton;
    Button eightButton;
    Button nineButton;
    Button subtractButton;
    Button fourButton;
    Button fiveButton;
    Button sixButton;
    Button plusButton;
    Button oneButton;
    Button twoButton;
    Button threeButton;
    Button enterButton;
    Button zeroButton;
    Button decimalPointButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadTextViews();
        loadButtons();
        calculator = new CalculatorUseCase(this);
    }

    private void loadTextViews() {
        stackViews = new TextView[5];
        stackViews[0] = (TextView) findViewById(R.id.stack_0_tv);
        stackViews[1] = (TextView) findViewById(R.id.stack_1_tv);
        stackViews[2] = (TextView) findViewById(R.id.stack_2_tv);
        stackViews[3] = (TextView) findViewById(R.id.stack_3_tv);
        stackViews[4] = (TextView) findViewById(R.id.stack_4_tv);
        sandpitView = (TextView) findViewById(R.id.sandpit_tv);
    }

    private void loadButtons() {
        undoButton = (Button) findViewById(R.id.undo_button);
        undoButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            try {
                calculator.undo();
            } catch (Exception e) {
                // ignore
            }
        }});
        powerButton = (Button) findViewById(R.id.power_button);
        powerButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            try {
                calculator.power();
            } catch (Exception e) {
                // ignore
            }
        }});
        swapButton = (Button) findViewById(R.id.swap_button);
        swapButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            try {
                calculator.swap();
            } catch (Exception e) {
                // ignore
            }
        }});
        deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            calculator.delete();
        }});
        redoButton = (Button) findViewById(R.id.redo_button);
        redoButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            try {
                calculator.redo();
            } catch (Exception e) {
                // ignore
            }
        }});
        reverseSignButton = (Button) findViewById(R.id.reverse_sign_button);
        reverseSignButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            try {
                calculator.sign();
            } catch (Exception e) {
                // ignore
            }
        }});
        divideButton = (Button) findViewById(R.id.divide_button);
        divideButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            try {
            calculator.divide();
        } catch (Exception e) {
            // ignore
        }
        }});
        multiplyButton = (Button) findViewById(R.id.multiply_button);
        multiplyButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            try {
                calculator.multiply();
            } catch (Exception e) {
                // ignore
            }
        }});
        sevenButton = (Button) findViewById(R.id.seven_button);
        sevenButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            calculator.digit(7);
        }});
        eightButton = (Button) findViewById(R.id.eight_button);
        eightButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            calculator.digit(8);
        }});
        nineButton = (Button) findViewById(R.id.nine_button);
        nineButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            calculator.digit(9);
        }});
        subtractButton = (Button) findViewById(R.id.subtract_button);
        subtractButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            try {
                calculator.subtract();
            } catch (Exception e) {
                // ignore
            }
        }});
        fourButton = (Button) findViewById(R.id.four_button);
        fourButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            calculator.digit(4);
        }});
        fiveButton = (Button) findViewById(R.id.five_button);
        fiveButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            calculator.digit(5);
        }});
        sixButton = (Button) findViewById(R.id.six_button);
        sixButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            calculator.digit(6);
        }});
        plusButton = (Button) findViewById(R.id.plus_button);
        plusButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            try {
                calculator.add();
            } catch (Exception e) {
                // ignore
            }
        }});
        oneButton = (Button) findViewById(R.id.one_button);
        oneButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            calculator.digit(1);
        }});
        twoButton = (Button) findViewById(R.id.two_button);
        twoButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            calculator.digit(2);
        }});
        threeButton = (Button) findViewById(R.id.three_button);
        threeButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            calculator.digit(3);
        }});
        enterButton = (Button) findViewById(R.id.enter_button);
        enterButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            calculator.enter();
        }});
        zeroButton = (Button) findViewById(R.id.zero_button);
        zeroButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            calculator.digit(0);
        }});
        decimalPointButton = (Button) findViewById(R.id.decimal_point_button);
        decimalPointButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            calculator.decimalPoint();
        }});
    }

    @Override
    public void update(@NotNull List<Double> stack, @NotNull String sandpit, boolean inSandpit) {
        drawSandpit(sandpit, inSandpit);
        drawStack(stack);
    }

    /*
    stacksize:0 i:0 should not show
    stacksize:1 i:0 should show i:1 should not
    stacksize:2 i:1 should show i:2 should not
    */
    private void drawStack(List<Double> stack) {
        for(int i = 0; i < 5; i++) {
            if (stack.size() > i) {
                stackViews[i].setText(stack.get(i).toString());
            } else {
                stackViews[i].setText("");
            }
        }
    }

    private void drawSandpit(String sandpit, boolean inSandpit) {
        if (inSandpit) {
            sandpitView.setVisibility(View.VISIBLE);
            sandpitView.setText(sandpit);
        } else {
            sandpitView.setVisibility(View.INVISIBLE);
        }
    }
}
