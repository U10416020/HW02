package tw.edu.utaipei.u10416020.hw02;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Eva on 2017/11/2.
 */

public class SameActivity extends AppCompatActivity {
    private Intent intent = new Intent();
    private TextView textCount;
    private Button btnStart, btnPause, btnStop, btnReset;
    public int checkCounter;
    private TableLayout tb;
    private LinearLayout linLay;
    public int num=1,no1=0;
    private ScrollView svResult;
    private final Handler mHandler = new Handler();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_same);
        intent = this.getIntent();
        btnStart = (Button)findViewById(R.id.buttonStart);
        btnPause = (Button)findViewById(R.id.buttonPause);
        btnStop = (Button)findViewById(R.id.buttonStop);
        btnReset = (Button)findViewById(R.id.buttonReset);
        textCount = (TextView)findViewById(R.id.textCount);
        linLay=(LinearLayout)findViewById(R.id.linearLayout1);
        svResult = (ScrollView) findViewById(R.id.scrollView2);



        btnStart.setOnClickListener(start);
        btnPause.setOnClickListener(pause);
        btnStop.setOnClickListener(stop);
        btnReset.setOnClickListener(reset);

    }

    private Button.OnClickListener start = new Button.OnClickListener(){
        public void onClick(View V){
            btnPause.setClickable(true);
            btnStop.setClickable(true);
            btnReset.setClickable(true);
            ThreadDeclaration();
        }
    };

    private Button.OnClickListener pause = new Button.OnClickListener(){
        public void onClick(View V){
            if(num==1)
                no1=checkCounter;
            linLay.addView(newLinear());
            mHandler.post(mScrollToBottom);
            num++;
        }
    };

    private Button.OnClickListener stop = new Button.OnClickListener(){
        public void onClick(View V){
            btnStart.setClickable(true);
            btnPause.setClickable(false);
            if(num==1)
                no1=checkCounter;
            linLay.addView(newLinear());
            mHandler.post(mScrollToBottom);
            num++;
        }
    };

    private Button.OnClickListener reset = new Button.OnClickListener(){
        public void onClick(View V){
            checkCounter=0;
            num=1;
            no1=0;
            textCount.setText(""+checkCounter);
            btnPause.setClickable(false);
            btnStop.setClickable(false);
            btnStart.setClickable(true);
            linLay.removeAllViews();
        }
    };

    private LinearLayout newLinear(){
        LinearLayout newLayout = new LinearLayout(SameActivity.this);
        newLayout.setOrientation(LinearLayout.HORIZONTAL);
        newLayout.setPadding(40,5,5,5);

        TextView view = new TextView(SameActivity.this);
        view.setTextSize(24);
        view.setText(""+num);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,1));

        TextView view1 = new TextView(SameActivity.this);
        view1.setTextSize(24);
        view1.setText(""+checkCounter);
        view1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,1));

        TextView view2 = new TextView(SameActivity.this);
        view2.setTextSize(24);
        view2.setText(""+(checkCounter-no1));
        view2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1));

        newLayout.addView(view);
        newLayout.addView(view1);
        newLayout.addView(view2);

        return newLayout;
    }

    private void ThreadDeclaration() {
        Thread t = new Thread() {
            @Override
            public void run() {
                btnStart.setClickable(false);
                while(btnStart.isClickable()==false){
                    sendMessage(1, checkCounter);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                    checkCounter+=1;
                }
                checkCounter=0;
            }
        };
        t.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Bundle b = msg.getData();
                    textCount.setText("" + b.getInt("COUNT_VALUE"));
                    break;
            }
        }
    };

    private void sendMessage(int msg, int count) {
        Message message = new Message();
        message.what = msg;//message code
        Bundle b = new Bundle();
        b.putInt("COUNT_VALUE", count);
        message.setData(b);
        handler.sendMessage(message);
    }

    private Runnable mScrollToBottom = new Runnable() {
        @Override
        public void run() {
            int off = linLay.getMeasuredHeight() - svResult.getHeight();
            if (off > 0) {
                svResult.scrollTo(0, off);
            }
        }
    };

}
