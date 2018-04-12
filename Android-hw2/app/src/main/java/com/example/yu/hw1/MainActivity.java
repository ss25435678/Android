package com.example.yu.hw1;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editText, editAge;
    private Button button;
    private TextView sex,age,suggestion;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editAge = (EditText) findViewById(R.id.editAge);
        editText = (EditText) findViewById(R.id.editSex);
        button = (Button) findViewById(R.id.button);
        suggestion = (TextView) findViewById(R.id.suggestion);

        button.setOnClickListener(buttonClick);

    }
    private View.OnClickListener buttonClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            String strsex = editText.getText().toString();
            int Age = Integer.parseInt(editAge.getText().toString());

            String strsuggestion = getString(R.string.suggestion);
            if (strsex.equals(getString(R.string.male))) {
                if (Age < 30) {
                    strsuggestion += getString(R.string.sug_not_hurry);
                } else if (Age <= 35 && Age >= 30) {
                    strsuggestion += getString(R.string.sug_get_married);
                } else if (Age > 35) {
                    strsuggestion += getString(R.string.sug_find_couple);
                }
            }
            else {
                if (Age < 28) {
                    strsuggestion += getString(R.string.sug_not_hurry);
                } else if (Age >= 28 && Age <= 32) {
                    strsuggestion += getString(R.string.sug_get_married);
                } else if (Age > 32) {
                    strsuggestion += getString(R.string.sug_find_couple);
                }
            }
            suggestion.setText(strsuggestion);
        }
    };

}
