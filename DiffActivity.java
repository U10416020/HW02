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
    private Button btnPlus;
    private LinearLayout linLayVer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_diff);
        intent = this.getIntent();
        linLayVer = (LinearLayout)findViewById(R.id.LinLayVer);
        linLayVer.addView(new Clock(DiffActivity.this));
        btnPlus = (Button)findViewById(R.id.buttonPlus);
        btnPlus.setOnClickListener(plus);

    }

    private Button.OnClickListener plus = new Button.OnClickListener(){
        public void onClick(View V){
            linLayVer.addView(new Clock(DiffActivity.this));
        }
    };
}
