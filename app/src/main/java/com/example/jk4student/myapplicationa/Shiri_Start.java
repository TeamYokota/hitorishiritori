package com.example.jk4student.myapplicationa;

/**
 * Created by jk4student on 2015/06/17.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Shiri_Start extends Activity {

    private EditText edit = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shiri_start);

        edit = (EditText)findViewById(R.id.Text);
        Button btn = (Button)findViewById(R.id.Button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // �C���e���g�ւ̃C���X�^���X����
                Intent intent = new Intent(Shiri_Start.this, MainActivity.class);
                //�@�C���e���g�ɒl���Z�b�g
                intent.putExtra("keyword", edit.getText().toString());
                // �T�u��ʂ̌Ăяo��
                startActivity(intent);
            }
        });
    }


}


