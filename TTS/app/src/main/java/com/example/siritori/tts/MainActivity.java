package com.example.siritori.tts;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Locale;
import java.util.Queue;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.os.Bundle;
import java.util.Locale;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringTokenizer;
import org.xmlpull.v1.XmlPullParser;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    final private Float SPEECH_SLOW = 0.5f;
    final private Float SPEECH_NORMAL = 1.0f;
    final private Float SPEECH_FAST = 1.5f;
    final private Float PITCH_LOW = 0.5f;
    final private Float PITCH_NORMAL = 1.0f;
    final private Float PITCH_HIGH = 1.5f;
    private TextToSpeech    tts;
    private Button buttonSpeech;
    private Button buttonSpeech2;
    private Button buttonSlow;
    private Button buttonNormal;
    private Button buttonFast;
    private Button buttonLowPitch;
    private Button buttonNormalPitch;
    private Button buttonHighPitch;

    Handler  mHandler   = new Handler();        //Handler�̃C���X�^���X����
    TextView mTextView;
    String src = new String();
    byte[] line = new byte[1024];
    int size;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView�擾
        mTextView = (TextView)findViewById(R.id.EditTextSpeech);
        // �{�^����ClickListener�̓o�^
        buttonSpeech = (Button)findViewById(R.id.ButtonSpeech);
        buttonSpeech.setOnClickListener(this);
        buttonSpeech2 = (Button)findViewById(R.id.ButtonSpeech2);
        buttonSpeech2.setOnClickListener(this);
        buttonSlow = (Button)findViewById(R.id.ButtonSlow);
        buttonSlow.setOnClickListener(this);
        buttonNormal = (Button)findViewById(R.id.ButtonNormal);
        buttonNormal.setOnClickListener(this);
        buttonFast = (Button)findViewById(R.id.ButtonFast);
        buttonFast.setOnClickListener(this);
        buttonLowPitch = (Button)findViewById(R.id.ButtonLowPitch);
        buttonLowPitch.setOnClickListener(this);
        buttonNormalPitch = (Button)findViewById(R.id.ButtonNormalPitch);
        buttonNormalPitch.setOnClickListener(this);
        buttonHighPitch = (Button)findViewById(R.id.ButtonHighPitch);
        buttonHighPitch.setOnClickListener(this);
        // TextToSpeech�I�u�W�F�N�g�̐���  ������
        tts = new TextToSpeech(this, this);
    }
    @Override    protected void onDestroy() {
        super.onDestroy();
        if (null != tts) {
            // TextToSpeech�̃��\�[�X���������
            tts.shutdown();
        }
    }
    //  @Override
    public void onInit(int status) {
        if (TextToSpeech.SUCCESS == status) {
            Locale locale = Locale.ENGLISH;
            if (tts.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
                tts.setLanguage(locale);
            } else {
                Log.d("", "Error SetLocale");
            }
        } else {
            Log.d("", "Error Init");
        }
    }
    private void speechText(String string) {
        //String string = ((EditText)findViewById(R.id.EditTextSpeech)).getText().toString();
        if (0 < string.length()) {
            if (tts.isSpeaking()) {
                // �ǂݏグ���Ȃ�~�߂�
                tts.stop();
            }       // �ǂݏグ�J�n
            tts.speak(string, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
    //       @Override
    public void onClick(View v) {
        if (buttonSpeech == v) {
            mTextView.setText("");
            httpWeather();
        }else if (buttonSpeech2 == v) {
            String string = ((EditText)findViewById(R.id.EditTextSpeech)).getText().toString();
            speechText(string);
        } else if (buttonSlow == v) {
            // �Đ����x�̐ݒ�
            tts.setSpeechRate(SPEECH_SLOW);
        } else if (buttonNormal == v) {
            // �Đ����x�̐ݒ�
            tts.setSpeechRate(SPEECH_NORMAL);
        } else if (buttonFast == v) {
            // �Đ����x�̐ݒ�
            tts.setSpeechRate(SPEECH_FAST);
        } else if (buttonLowPitch == v) {
            // �Đ��s�b�`�̐ݒ�
            tts.setPitch(PITCH_LOW);
        } else if (buttonNormalPitch == v) {
            // �Đ��s�b�`�̐ݒ�
            tts.setPitch(PITCH_NORMAL);
        } else if (buttonHighPitch == v) {
            // �Đ��s�b�`�̐ݒ�
            tts.setPitch(PITCH_HIGH);
        }
    }
    private void httpWeather() {
        // �X���b�h�N��
        (new Thread(new Runnable() {
            //   @Override
            public void run() {
                // �ʏ�o�b�N�O�����h�������ɋL�q���܂�
                int intEventType = -1;
                String strParserName = "";
                StringTokenizer  objToken = null;
                String strGettingTitle = "";
                String strGettingTelop = "";
                String strGettingDiscription = "";

                HttpURLConnection http = null;
                InputStream in = null;
                mTextView = (TextView)findViewById(R.id.EditTextSpeech);
                try{
                    //�擾XML��URI��ݒ�
                    //uri += "?city=" + id + "&day=tomorrow";
                    URL uri = new URL("http://weather.livedoor.com/forecast/webservice/rest/v1?city=54&day=tomorrow");
                    //XML�擾
                    http = (HttpURLConnection)uri.openConnection();
                    http.setRequestMethod("GET");
                    http.connect();
                    // �f�[�^���擾
                    in = http.getInputStream();
                    //XML������𒊏o
                    XmlPullParser parser = Xml.newPullParser();
                    parser.setInput(in,"UTF-8");
                    while(intEventType != XmlPullParser.END_DOCUMENT){
                        switch (intEventType){
                            case XmlPullParser.START_TAG:
                                strParserName = parser.getName() ;
                                if(strParserName.equals("title") && strGettingTitle.equals("")){
                                    strGettingTitle = parser.nextText()+"�́A";
                                }
                                if(strParserName.equals("telop") && strGettingTelop.equals("")){
                                    strGettingTelop = parser.nextText()+"�ł��A";
                                }
                                if(strParserName.equals("description") && strGettingDiscription.equals("")){
                                    strGettingDiscription = parser.nextText();
                                    String[] strAry=strGettingDiscription.split("�B",2);
                                    strGettingDiscription=strAry[0]; //description��1���̂ݕ\��
                                }
                        }
                        if(!strParserName.equals("") && !strGettingTelop.equals("") && !strGettingDiscription.equals("")){
                            break;
                        }
                        intEventType = parser.next();
                    }
                    //�擾�f�[�^����ʂɕ\��
                    src=strGettingTitle + "\n" + strGettingTelop + "\n" + strGettingDiscription;
                }catch(Exception e){
                    src=e.toString();
                }finally{
                }
                mHandler.post(new Runnable() {
                    // @Override
                    public void run() {
                        //�擾�����C���[�W��ImageView�ɐݒ�
                        mTextView.setText(src);
                    }
                });
                speechText(src);
            }
        })).start();
    }
}