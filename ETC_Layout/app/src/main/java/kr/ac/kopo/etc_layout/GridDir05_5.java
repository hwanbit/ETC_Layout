package kr.ac.kopo.etc_layout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GridDir05_5 extends AppCompatActivity {
    EditText edit1, edit2;
    Button[] btnNums = new Button[10];
    Button btnPlus, btnMinus, btnMultiply, btnDivide;
    TextView textResult;
    int[] btnNumIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                        R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};

    String num1 = "";
    String num2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.grid_dir05_5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);

        for(int i=0; i<btnNums.length; i++) {
            btnNums[i] = findViewById(btnNumIds[i]);
        }

        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnMultiply = findViewById(R.id.btn_multiply);
        btnDivide = findViewById(R.id.btn_divide);

        textResult = findViewById(R.id.text_result);

        // 아래 for문 작성으로 인해 독립적으로 onClickListener 설정 가능
        for (int i=0; i<btnNums.length; i++) {
            final int index = i;
            btnNums[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edit1.isFocused()) {
                        num1 = edit1.getText().toString() + btnNums[index].getText().toString();
                        edit1.setText(num1);
                    } else if (edit2.isFocused()) {
                        num2 = edit2.getText().toString() + btnNums[index].getText().toString();
                        edit2.setText(num2);
                    } else {
                        Toast.makeText(getApplicationContext(), "먼저 EditText를 선택해 주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        btnPlus.setOnClickListener(btnListener);
        btnMinus.setOnClickListener(btnListener);
        btnMultiply.setOnClickListener(btnListener);
        btnDivide.setOnClickListener(btnListener);
    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            Button eventBtn = (Button) v;
            int num1 = Integer.parseInt(edit1.getText().toString());
            int num2 = Integer.parseInt(edit2.getText().toString());
            int result = 0;

            if(eventBtn == btnPlus) {
                result = num1 + num2;
            }else if(eventBtn == btnMinus) {
                result = num1 - num2;
            }else if(eventBtn == btnMultiply) {
                result = num1 * num2;
            }else if (eventBtn == btnDivide) {
                try {
                    result = num1 / num2;
                } catch (ArithmeticException e) {
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            textResult.setText("계산 결과: " + result);
        }
    };
}