package tech.blur.firsttestapp.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tech.blur.firsttestapp.R;
import tech.blur.firsttestapp.core.model.Post;
import tech.blur.firsttestapp.moxy.MvpAndroidxActivity;

public class ListActivity extends MvpAndroidxActivity implements ListView {

//TODO доделать ресайклер

    @InjectPresenter
    ListPresenter presenter;
    private PostAdapter postAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public static void start(Context context){
        final Intent intent = new Intent(context, ListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        postAdapter = new PostAdapter(this, (chipGroup, i, post, pos) -> {
            presenter.priorityChanged(pos, i);
        });

        recyclerView.setAdapter(postAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    public void onPostsReady(ArrayList<Post> posts) {
        postAdapter.setPosts(posts);
        postAdapter.notifyDataSetChanged();
    }
}
