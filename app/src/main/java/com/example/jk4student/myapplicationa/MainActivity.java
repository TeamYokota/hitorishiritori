package com.example.jk4student.myapplicationa;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;






public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.TestText);

            // �C���e���g���擾
            Intent intent = getIntent();
            // �C���e���g�ɕۑ����ꂽ�f�[�^���擾
            String sp = intent.getStringExtra("keyword");
            textView.setText(sp);



        Button btn = (Button)findViewById(R.id.TestButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.TestEdit);
                TextView textView = (TextView) findViewById(R.id.TestText);

                SpannableStringBuilder sp = (SpannableStringBuilder) edit.getText();
                Log.v("onCreate", sp.toString());

                // �擾����������TextView�ɃZ�b�g�I
                textView.setText(sp);
            }

        });
        }
}