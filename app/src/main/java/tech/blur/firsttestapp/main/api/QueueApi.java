package tech.blur.firsttestapp.main.api;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import tech.blur.firsttestapp.core.model.Post;

public interface QueueApi {

    @GET("posts")
    Single<List<Post>> getPosts();

    @GET("posts/{id}")
    Single<Post> getPost(String id);

}
