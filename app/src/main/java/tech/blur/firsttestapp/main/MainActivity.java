package tech.blur.firsttestapp.main;

import androidx.cardview.widget.CardView;
import tech.blur.firsttestapp.R;
import tech.blur.firsttestapp.core.model.Post;
import tech.blur.firsttestapp.list.ListActivity;
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
import com.google.android.material.chip.ChipGroup;

public class MainActivity extends MvpAndroidxActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    TextView title;
    TextView body;
    Button done;
    CardView cardView;
    ChipGroup chipGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        body = findViewById(R.id.body);
        done = findViewById(R.id.done);
        title = findViewById(R.id.title);
        cardView = findViewById(R.id.cardView);
        chipGroup = findViewById(R.id.chip_group);

        done.setOnClickListener(event -> presenter.onDoneClicked());

        presenter.getQueue();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem update = menu.findItem(R.id.update);
        MenuItem list = menu.findItem(R.id.list);
        MenuItem add = menu.findItem(R.id.add);

        list.setOnMenuItemClickListener(menuItem -> {
            ListActivity.start(this);
            return true;
        });

        update.setOnMenuItemClickListener(menuItem -> {
            presenter.updateQueue();
            return true;
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void setTask(Post post) {
        chipGroup.setVisibility(View.VISIBLE);
        cardView.setCardBackgroundColor(getResources().getColor(R.color.cardTask));
        String titleS = post.getId() + ". " + post.getTitle();
        body.setText(post.getBody());
        title.setText(titleS);
        switch (post.getPriority()) {
            case 0: {
                chipGroup.check(R.id.priority_normal);
                break;
            }
            case 1: {
                chipGroup.check(R.id.priority_middle);
                break;
            }
            case 2: {
                chipGroup.check(R.id.priority_high);
                break;
            }
        }
    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTaskEnd() {
        title.setText("Ура");
        body.setText("Все задания выполнены");
        chipGroup.setVisibility(View.GONE);
        cardView.setCardBackgroundColor(getResources().getColor(R.color.cardTaskEnd));

    }

    @Override
    public void onDownloadReady() {
        //
    }
}
