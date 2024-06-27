package test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private String currentInput = "";
    private String operator = "";
    private BigDecimal operand1 = BigDecimal.ZERO;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cal_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        display = findViewById(R.id.display);

        // Set click listeners for number buttons
        findViewById(R.id.btn_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendInput("0");
            }
        });
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendInput("1");
            }
        });
        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendInput("2");
            }
        });
        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendInput("3");
            }
        });
        findViewById(R.id.btn_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendInput("4");
            }
        });
        findViewById(R.id.btn_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendInput("5");
            }
        });
        findViewById(R.id.btn_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendInput("6");
            }
        });
        findViewById(R.id.btn_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendInput("7");
            }
        });
        findViewById(R.id.btn_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendInput("8");
            }
        });
        findViewById(R.id.btn_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendInput("9");
            }
        });
        findViewById(R.id.btn_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendInput(".");
            }
        });

        // Set click listeners for operator buttons
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperator("+");
            }
        });
        findViewById(R.id.btn_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperator("-");
            }
        });
        findViewById(R.id.btn_multiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperator("*");
            }
        });
        findViewById(R.id.btn_divide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperator("/");
            }
        });

        // Set click listener for equal button
        findViewById(R.id.btn_equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateResult();
            }
        });

        // Set click listener for clear button
        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
    }

    // Append input to the display
    private void appendInput(String text) {
        currentInput += text;
        display.setText(currentInput);
    }

    // Set the operator and store the first operand
    private void setOperator(String op) {
        if (!currentInput.isEmpty()) {
            operand1 = new BigDecimal(currentInput);
            operator = op;
            currentInput = "";
        }
    }

    // Calculate the result based on the operator and the second operand
    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            BigDecimal operand2 = new BigDecimal(currentInput);
            BigDecimal result = BigDecimal.ZERO;
            switch (operator) {
                case "+":
                    result = operand1.add(operand2);
                    break;
                case "-":
                    result = operand1.subtract(operand2);
                    break;
                case "*":
                    result = operand1.multiply(operand2);
                    break;
                case "/":
                    if (operand2.equals(BigDecimal.ZERO)) {
                        display.setText("Error");
                        return;
                    }
                    result = operand1.divide(operand2, 10, BigDecimal.ROUND_HALF_UP);
                    break;
            }
            display.setText(result.toString());
            currentInput = result.toString();
            operator = "";
            operand1 = BigDecimal.ZERO;
        }
    }

    // Clear the display, reset variables
    private void clear() {
        currentInput = "";
        operator = "";
        operand1 = BigDecimal.ZERO;
        display.setText("0");
    }
}