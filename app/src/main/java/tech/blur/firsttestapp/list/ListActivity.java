package tech.blur.firsttestapp.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import tech.blur.firsttestapp.moxy.MvpAndroidxActivity;

public class ListActivity extends MvpAndroidxActivity implements ListView {



    @InjectPresenter
    ListPresenter presenter;

    public static void start(Context context){
        final Intent intent = new Intent(context, ListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
