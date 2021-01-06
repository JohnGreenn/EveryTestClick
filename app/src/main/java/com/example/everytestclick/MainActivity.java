package com.example.everytestclick;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv);
        String string = new String("The trial has been adjourned until.");
        List<String> text = new ArrayList<>();
        String[] re = string.split(" ");
        for(int i = 0; i <re.length ; i++) {
            text.add(re[i]);
        }
        showText(mTv,string,text);
    }
    class NoLineClickSpan extends ClickableSpan {
        String text;
        public NoLineClickSpan(String text) {
            super();
            this.text = text;
        }
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.parseColor("#000000"));
            ds.setUnderlineText(false); //去掉下划线
        }
        @Override
        public void onClick(View widget) {
            //这里可以请求接口 查询单词的意思 然后做弹窗填数据
            Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
        }
    }
    private void showText(TextView textView,String string ,List<String> text){
        SpannableString spannableString = new SpannableString(string);
        for (int i=0;i<text.size();i++){
            String clickableLink = text.get(i);
            int length = text.get(i).length();
            int startIndex=string.indexOf(clickableLink);
            ClickableSpan clickSpan = new NoLineClickSpan(clickableLink); //设置超链接
            spannableString.setSpan(clickSpan, startIndex, startIndex+length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        textView.append(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}

