package tech.blur.firsttestapp.Queue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tech.blur.firsttestapp.R;

public class QueueActivity extends AppCompatActivity implements QueueView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
