package tech.blur.firsttestapp.list;

import android.content.SharedPreferences;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import tech.blur.firsttestapp.App;
import tech.blur.firsttestapp.R;
import tech.blur.firsttestapp.core.PreferencesApi;
import tech.blur.firsttestapp.core.model.Post;

@InjectViewState
public class ListPresenter extends MvpPresenter<ListView> {

    @Inject
    SharedPreferences preferences;
    private ArrayList<Post> posts = new ArrayList<>();

    public ListPresenter() {
        App.component.inject(this);
        downloadPosts();
    }

    private void downloadPosts() {
        if (PreferencesApi.getPosts(preferences) != null) {
            posts.addAll(PreferencesApi.getPosts(preferences));
            getViewState().onPostsReady(posts);
        }
    }

    public void priorityChanged(int pos, int chipRes) {
        switch (chipRes){
            case R.id.item_priority_normal:{
                posts.get(pos).setPriority(0);
                break;
            }
            case R.id.item_priority_middle:{
                posts.get(pos).setPriority(1);
                break;
            }
            case R.id.item_priority_high:{
                posts.get(pos).setPriority(2);
                break;
            }
        }
        PreferencesApi.setPosts(posts, preferences);
    }
}
