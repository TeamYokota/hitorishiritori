package com.example.siritori.vosound;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity {

    SoundPool sp;
    int sound_id;

            /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        setContentView(ll);

        //インスタンス作成
        sp       = new SoundPool( 1, AudioManager.STREAM_MUSIC, 0 );
        sound_id = sp.load(this, R.raw.test, 1 );

        Button btn = new Button(this);
        btn.setText("ボタンを押すと音がなります");
        btn.setOnClickListener( new OnClickListener(){
            @Override
            public void onClick(View v) {
                sp.play(sound_id, 1.0F, 1.0F, 0, 0, 1.0F);
            }

        } );
        ll.addView(btn);
    }

    @Override
    public void onPause(){
        super.onPause();
        sp.release();
    }
}
