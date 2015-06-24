package com.example.siritori.textcheck;

        import android.support.v7.app.ActionBarActivity;
        import android.util.Log;
        import android.app.Activity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.LinearLayout;


public class TextCheck extends Activity implements OnClickListener {

    private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    private Button button;

    @Override protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        setContentView(linearLayout);

        button = new Button(this);
        button.setText("Count");
        button.setOnClickListener(this);
        linearLayout.addView(button,
                new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
    }

    public void onClick(View v)  {

        String str  = "^[ぁ-ゞ]+$";
        String str1 = "^{1,13}$";
        String stop = "ちゅうだん";
        String end  = "ん";
        String text  = "しりとり";
        String text2 = "しんぶんし";
        String text3 = "よっしー";
        String nagai = "あああああいいいいいううううう";
        String haihun = "ー";

        button.setOnClickListener(this);

        for (int x = 0; x < 3; x++) {

            if("ちゅうだん".equals(stop)) {
                System.out.println("ちゅうだんするため中断処理へ移行する");
                Log.d("tag", "中断処理へ飛ばす");
            } else {
                System.out.println("中断しない");
                Log.d("tag", "次へ");
            }

            if(nagai.matches(str1)) {
                System.out.println("13文字以内");
                Log.d("tag", "13文字以内");
                //入力文字数が適正
            } else {
                System.out.println("長いor入力なし");
                Log.d("tag", "長いor入力なし");
                //入力文字数が不適正
                //break;
            }

            //////////////////////////////////

            //このタイミングで文頭と語尾の判定

            //////////////////////////////////

            if("ぴーたーぱん".endsWith(end)) {
                System.out.println("語尾が「ん」です");
                Log.d("tag", "勝敗処理へ");
            } else {
                System.out.println("語尾は「ん」以外です");
                Log.d("tag", "続く");
            }

            if(text3.endsWith(haihun)) {
                System.out.println("語尾はハイフン");
                Log.d("tag", "ー");
                //この場合はーを排除し次に進みます
           } else {
                System.out.println("後方一致ではありません");
                Log.d("tag", "続く");
                //特に処理せず次へ
            }

            if(text.equals(str)) {
                System.out.println("日本語");
                Log.d("tag", "全角日本語");
                //入力文字が日本語の場合
            } else {
                System.out.println("特殊文字や数字が含まれる");
                Log.d("tag", "入力しなおし");
                //数字などが含まれる場合
            }

            if(text.equals(str)) { //重複処理
                //DBへアクセスし値を取得し右辺へ代入
                //左辺へは入力された文字を代入
                System.out.println("一度使われた文字です");
                Log.d("tag", "再入力させるためループの頭へ");
            } else {
                System.out.println("初使用です");
                Log.d("tag", "次へ");
            }

            ///////////////////

            //濁点半濁点を＝にする処理はここ

            //////////////////


            //////////////////

            //文字の画面＆音声出力はここで行う

            //////////////////


        }






    }
}