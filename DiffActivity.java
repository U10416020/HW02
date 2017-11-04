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
import android.widget.TextView;

/**
 * Created by Eva on 2017/11/2.
 */

public class DiffActivity extends AppCompatActivity {
    private Intent intent = new Intent();
    private TextView textCount1, textCount2, textCount3;
    private Button btnPlus,btnStart1, btnPause1, btnReset1,btnStart2, btnPause2, btnReset2,btnStart3, btnPause3, btnReset3;
    private ImageButton imageButton;
    private LinearLayout linLayVer;
    public long startTime1,startTime2,startTime3,offset1=0,offset2=0,offset3=0;
    public String rst="00";
    public int restart1=0,restart2,restart3, number=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_diff);
        intent = this.getIntent();
        linLayVer = (LinearLayout)findViewById(R.id.LinLayVer);
        btnPlus = (Button)findViewById(R.id.buttonPlus);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        textCount1 = (TextView)findViewById(R.id.textCount1);
        btnStart1 = (Button)findViewById(R.id.buttonStart1);
        btnPause1 = (Button)findViewById(R.id.buttonPause1);
        btnReset1 = (Button)findViewById(R.id.buttonReset1);

        textCount2 = (TextView)findViewById(R.id.textCount2);
        btnStart2 = (Button)findViewById(R.id.buttonStart2);
        btnPause2 = (Button)findViewById(R.id.buttonPause2);
        btnReset2 = (Button)findViewById(R.id.buttonReset2);

        textCount3 = (TextView)findViewById(R.id.textCount3);
        btnStart3 = (Button)findViewById(R.id.buttonStart3);
        btnPause3 = (Button)findViewById(R.id.buttonPause3);
        btnReset3 = (Button)findViewById(R.id.buttonReset3);

        btnStart1.setOnClickListener(start);
        btnPause1.setOnClickListener(pause);
        btnReset1.setOnClickListener(reset);

        btnStart2.setOnClickListener(start);
        btnPause2.setOnClickListener(pause);
        btnReset2.setOnClickListener(reset);

        btnStart3.setOnClickListener(start);
        btnPause3.setOnClickListener(pause);
        btnReset3.setOnClickListener(reset);

        btnPlus.setOnClickListener(plus);

    }

    private Button.OnClickListener plus = new Button.OnClickListener(){
        public void onClick(View V){
            //linLayVer.addView(newLinear());
            linLayVer.addView(new Clock(DiffActivity.this));
        }
    };

    private LinearLayout newLinear(){
        LinearLayout newLayout = new LinearLayout(DiffActivity.this);
        newLayout.setPadding(0,10,5,10);
        newLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView view = new TextView(DiffActivity.this);
        view.setTextSize(24);
        view.setText("00");
        view.setGravity(1);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,1));

        Button btstart = new Button(DiffActivity.this);
        btstart.setTextSize(24);
        btstart.setText(R.string.start);
        btstart.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        btstart.setOnClickListener(start);
        //btstart.setId(R.id);


        Button btpause = new Button(DiffActivity.this);
        btpause.setTextSize(24);
        btpause.setText(R.string.pause);
        btpause.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        btpause.setOnClickListener(pause);

        Button btreset = new Button(DiffActivity.this);
        btreset.setTextSize(24);
        btreset.setText(R.string.reset);
        btreset.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        btreset.setOnClickListener(reset);

        newLayout.addView(view);
        newLayout.addView(btstart);
        newLayout.addView(btpause);
        newLayout.addView(btreset);

        return newLayout;
    }

    private Button.OnClickListener start = new Button.OnClickListener(){
        public void onClick(View V){
            switch (V.getId()){
                case R.id.buttonStart1:
                    startTime1=System.currentTimeMillis();
                    ThreadDeclaration(1,startTime1);
                    btnPause1.setClickable(true);
                    btnReset1.setClickable(true);
                    break;
                case R.id.buttonStart2:
                    startTime2=System.currentTimeMillis();
                    ThreadDeclaration(2,startTime2);
                    btnPause2.setClickable(true);
                    btnReset2.setClickable(true);
                    break;
                case R.id.buttonStart3:
                    startTime3=System.currentTimeMillis();
                    ThreadDeclaration(3,startTime3);
                    btnPause3.setClickable(true);
                    btnReset3.setClickable(true);
                    break;
            }
        }
    };

    private Button.OnClickListener pause = new Button.OnClickListener(){
        public void onClick(View V){
            switch (V.getId()){
                case R.id.buttonPause1:
                    btnStart1.setClickable(true);
                    restart1=1;
                    String i = textCount1.getText().toString();
                    offset1=Long.valueOf(i);
                    break;
                case R.id.buttonPause2:
                    btnStart2.setClickable(true);
                    restart2=1;
                    String i1 = textCount2.getText().toString();
                    offset2=Long.valueOf(i1);
                    break;
                case R.id.buttonPause3:
                    btnStart3.setClickable(true);
                    restart3=1;
                    String i2 = textCount3.getText().toString();
                    offset3=Long.valueOf(i2);
                    break;
            }
        }
    };

    private Button.OnClickListener reset = new Button.OnClickListener(){
        public void onClick(View V){
            switch (V.getId()){
                case R.id.buttonReset1:
                    textCount1.setText(rst);
                    btnStart1.setClickable(true);
                    btnPause1.setClickable(false);
                    restart1=0;
                    offset1=0;
                    break;
                case R.id.buttonReset2:
                    textCount2.setText(rst);
                    btnStart2.setClickable(true);
                    btnPause2.setClickable(false);
                    restart2=0;
                    offset2=0;
                    break;
                case R.id.buttonReset3:
                    textCount3.setText(rst);
                    btnStart3.setClickable(true);
                    btnPause3.setClickable(false);
                    restart3=0;
                    offset3=0;
                    break;
            }
        }
    };

    private void ThreadDeclaration(int num,long stTime) {
        if(num==1){
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    btnStart1.setClickable(false);
                    while(btnStart1.isClickable()==false){
                        Long spentTime = System.currentTimeMillis() - startTime1;
                        Long seconds = spentTime/1000;

                        if(restart1==0){
                            sendMessage(1, seconds);
                        }
                        else if(restart1==1){
                            sendMessage(1, seconds+offset1);
                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {}
                    }
                }
            };
            t1.start();
        }
        else if(num==2){
            Thread t2 = new Thread() {
                @Override
                public void run() {
                    btnStart2.setClickable(false);
                    while(btnStart2.isClickable()==false){
                        Long spentTime = System.currentTimeMillis() - startTime2;
                        Long seconds = (spentTime/1000) % 60;

                        if(restart2==0){
                            sendMessage(2, seconds);
                        }
                        else if(restart2==1){
                            sendMessage(2, seconds+offset2);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {}
                    }

                }
            };
            t2.start();
        }
        else if(num==3){
            Thread t3 = new Thread() {
                @Override
                public void run() {
                    btnStart3.setClickable(false);
                    while(btnStart3.isClickable()==false){
                        Long spentTime = System.currentTimeMillis() - startTime3;
                        Long seconds = (spentTime/1000) % 60;

                        if(restart3==0){
                            sendMessage(3, seconds);
                        }
                        else if(restart3==1){
                            sendMessage(3, seconds+offset3);
                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {}
                    }

                }
            };
            t3.start();
        }

    }

    private void sendMessage(int msg, long count) {
        Message message = new Message();
        message.what = msg;//message code
        Bundle b = new Bundle();
        b.putLong("COUNT_VALUE", count);
        message.setData(b);
        handler.sendMessage(message);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle b = msg.getData();
            switch (msg.what) {
                case 1:

                    textCount1.setText("" + b.getLong("COUNT_VALUE"));
                    break;
                case 2:

                    textCount2.setText("" + b.getLong("COUNT_VALUE"));
                    break;
                case 3:

                    textCount3.setText("" + b.getLong("COUNT_VALUE"));
                    break;
            }
        }
    };

}
