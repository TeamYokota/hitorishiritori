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

    public void onClick(View v) {

        String str = "^[ぁ-ゞ]+$";
        String str1 = "^{1,13}$";
        String stop = "ちゅうだん";
        String end = "ん";
        String text = "しりとり";
        String text2 = "しんぶんし";
        String text3 = "よっしー";
        String nagai = "あああああいいいいいううううう";
        String haihun = "ー";
        String moji1 = "しりとり";
        String moji2 = "りんご";
        String moji3 = "らんらんる";

        button.setOnClickListener(this);

        for (int x = 0; x < 1; x++) {

            //中断処理
            if ("ちゅうだん".equals(stop)) {
                System.out.println("ちゅうだんするため中断処理へ移行する");
                Log.d("tag", "中断処理へ飛ばす");
            } else {
                System.out.println("中断しない");
                Log.d("tag", "次へ");
            }

            //13文字判定
            if (nagai.matches(str1)) {
                System.out.println("13文字以内");
                Log.d("tag", "13文字以内");
                //入力文字数が適正
            } else {
                System.out.println("長いor入力なし");
                Log.d("tag", "長いor入力なし");
                //入力文字数が不適正
                //break;
            }


            //語頭語尾一致確認
            if (moji1.endsWith(moji2.substring(0, 1))) {

                System.out.println("語頭語尾一致");
                Log.d("tag", "OK");

            } else {

                System.out.println("語頭語尾一致");
                Log.d("tag", "NO");

            }

            //”ん”の判定
            if ("ぴーたーぱん".endsWith(end)) {
                System.out.println("語尾が「ん」です");
                Log.d("tag", "勝敗処理へ");
            } else {
                System.out.println("語尾は「ん」以外です");
                Log.d("tag", "続く");
            }

            //ハイフンの除去
            if (text3.endsWith(haihun)) {
                System.out.println("語尾はハイフン");
                Log.d("tag", "ー");
                int n = text3.length();
                text3 = text3.substring(0,n-1);
                System.out.println(text3);
                //この場合はーを排除し次に進みます
            } else {
                System.out.println("後方一致ではありません");
                Log.d("tag", "続く");
                //特に処理せず次へ
            }

            //特殊文字などの排除
            if (text.equals(str)) {
                System.out.println("日本語");
                Log.d("tag", "全角日本語");
                //入力文字が日本語の場合
            } else {
                System.out.println("特殊文字や数字が含まれる");
                Log.d("tag", "入力しなおし");
                //数字などが含まれる場合
            }

            //重複確認
            if (text.equals(str)) {
                //DBへアクセスし値を取得し右辺へ代入
                //左辺へは入力された文字を代入
                System.out.println("一度使われた文字です");
                Log.d("tag", "再入力させるためループの頭へ");
            } else {
                System.out.println("初使用です");
                Log.d("tag", "次へ");
            }

        }
    }
}