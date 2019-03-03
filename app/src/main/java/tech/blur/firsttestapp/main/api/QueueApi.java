package tech.blur.firsttestapp.main.api;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import tech.blur.firsttestapp.core.model.PostServer;

public interface QueueApi {

    @GET("posts")
    Single<List<PostServer>> getPosts();

    @GET("posts/{id}")
    Single<PostServer> getPost(String id);

}
