package tech.blur.firsttestapp.Queue;

import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import retrofit2.Retrofit;
import tech.blur.firsttestapp.Queue.api.QueueApi;

public class QueuePresenter extends MvpPresenter<QueueView> {

    @Inject
    Retrofit retrofit;
    private QueueApi queueApi;

    public QueuePresenter() {

        queueApi = retrofit.create(QueueApi.class);
    }
}
