package tw.edu.utaipei.u10416020.hw02;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.R.drawable.btn_dialog;

/**
 * Created by Eva on 2017/11/5.
 */

public class Clock extends LinearLayout {
    LinearLayout newLayout,thisLayout;
    TextView view;
    Button btstart, btpause, btreset;
    ImageButton imageButton;
    long startTime=0,offset=0;
    int restart=0;
    String rst="00";

    @Override
    public void removeAllViews(){
        super.removeAllViews();
    }

    public Clock(Context context) {
        super(context);
        //this.addView(onInit(context));
        this.setPadding(0,10,5,10);
        this.setOrientation(LinearLayout.HORIZONTAL);
        //this.setOrientation(LinearLayout.VERTICAL);
        //this.addView(onInit(context));


        this.addView(setView(context));
        this.addView(setBtnStart(context));
        this.addView(setBtnPause(context));
        this.addView(setBtnReset(context));
        this.addView(setImageBtn(context));
    }

    public LinearLayout onInit(Context context){
        newLayout = new LinearLayout(context);
        newLayout.setPadding(0,10,5,10);
        newLayout.setOrientation(LinearLayout.HORIZONTAL);

        view = new TextView(context);
        view.setTextSize(24);
        view.setText("00");
        view.setGravity(1);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,1));

        btstart = new Button(context);
        btstart.setTextSize(24);
        btstart.setText(R.string.start);
        btstart.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        btstart.setOnClickListener(start);


        btpause = new Button(context);
        btpause.setTextSize(24);
        btpause.setText(R.string.pause);
        btpause.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        btpause.setOnClickListener(pause);

        btreset = new Button(context);
        btreset.setTextSize(24);
        btreset.setText(R.string.reset);
        btreset.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        btreset.setOnClickListener(reset);

        imageButton = new ImageButton(context);
        btpause.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        imageButton.setImageResource(btn_dialog);
        imageButton.setOnClickListener(imgBtn);

        newLayout.addView(view);
        newLayout.addView(btstart);
        newLayout.addView(btpause);
        newLayout.addView(btreset);
        newLayout.addView(imageButton);
        return newLayout;
    }

    public TextView setView(Context context){
        view = new TextView(context);
        view.setTextSize(24);
        view.setText("00");
        view.setGravity(1);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,1));
        return view;
    }

    public Button setBtnStart(Context context){
        btstart = new Button(context);
        btstart.setTextSize(24);
        btstart.setText(R.string.start);
        btstart.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        btstart.setOnClickListener(start);
        return btstart;
    }

    public Button setBtnPause(Context context){
        btpause = new Button(context);
        btpause.setTextSize(24);
        btpause.setText(R.string.pause);
        btpause.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        btpause.setOnClickListener(pause);
        return btpause;
    }

    public Button setBtnReset(Context context){
        btreset = new Button(context);
        btreset.setTextSize(24);
        btreset.setText(R.string.reset);
        btreset.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        btreset.setOnClickListener(reset);
        return btreset;
    }

    public ImageButton setImageBtn(Context context){
        imageButton = new ImageButton(context);
        btpause.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        imageButton.setImageResource(btn_dialog);
        imageButton.setOnClickListener(imgBtn);
        return imageButton;
    }

    private ImageButton.OnClickListener imgBtn = new ImageButton.OnClickListener(){
        public void onClick(View V){
            removeAllViews();
        }
    };

    private Button.OnClickListener start = new Button.OnClickListener(){
        public void onClick(View V){
            startTime=System.currentTimeMillis();
            ThreadDeclaration(1,startTime);
            btpause.setClickable(true);
            btreset.setClickable(true);
        }
    };

    private Button.OnClickListener pause = new Button.OnClickListener(){
        public void onClick(View V){
            btstart.setClickable(true);
            restart=1;
            String i = view.getText().toString();
            offset=Long.valueOf(i);
        }
    };

    private Button.OnClickListener reset = new Button.OnClickListener(){
        public void onClick(View V){
            view.setText(rst);
            btstart.setClickable(true);
            btpause.setClickable(false);
            restart=0;
            offset=0;
        }
    };

    private void ThreadDeclaration(int num,long stTime) {
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    btstart.setClickable(false);
                    while(btstart.isClickable()==false){
                        Long spentTime = System.currentTimeMillis() - startTime;
                        Long seconds = spentTime/1000;
                        if(restart==0){
                            sendMessage(1, seconds);
                        }
                        else if(restart==1){
                            sendMessage(1, seconds+offset);
                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {}
                    }
                }
            };
            t1.start();
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
                    view.setText("" + b.getLong("COUNT_VALUE"));
                    break;
            }
        }
    };
}
