package tech.blur.firsttestapp.main;

import androidx.cardview.widget.CardView;
import tech.blur.firsttestapp.R;
import tech.blur.firsttestapp.core.model.Post;
import tech.blur.firsttestapp.moxy.MvpAndroidxActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.zip.Inflater;

public class MainActivity extends MvpAndroidxActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    TextView title;
    TextView body;
    Button done;
    CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        body = findViewById(R.id.body);
        done = findViewById(R.id.done);
        title = findViewById(R.id.title);
        cardView = findViewById(R.id.cardView);

        done.setOnClickListener(event -> presenter.onDoneClicked());

        presenter.getQueue();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem settings = menu.findItem(R.id.settings);
        MenuItem history = menu.findItem(R.id.history);
        MenuItem add = menu.findItem(R.id.add);

        //settings.setOnMenuItemClickListener()
        //history.setOnMenuItemClickListener()

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void setTask(Post post) {
        cardView.setCardBackgroundColor(getResources().getColor(R.color.cardTask));
        String titleS = post.getId() + ". " + post.getTitle();
        body.setText(post.getBody());
        title.setText(titleS);
    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTaskEnd() {
        title.setText("Ура");
        title.setText("Все задания выполнены");
        cardView.setCardBackgroundColor(getResources().getColor(R.color.cardTaskEnd));

    }

    @Override
    public void onDownloadReady() {
        //
    }
}
