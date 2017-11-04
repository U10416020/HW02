package tw.edu.utaipei.u10416020.hw02;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button buttonDiff, buttonSame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSame = (Button)findViewById(R.id.buttonSame);
        buttonDiff = (Button)findViewById(R.id.buttonDiff);

        buttonSame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SameActivity.class);
                startActivity(intent);
            }
        });

        buttonDiff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DiffActivity.class);
                startActivity(intent);
            }
        });
    }


}
