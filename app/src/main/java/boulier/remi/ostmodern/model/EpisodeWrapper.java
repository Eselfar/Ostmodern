package boulier.remi.ostmodern.model;

import android.util.Log;

import boulier.remi.ostmodern.retrofit.Episode;
import boulier.remi.ostmodern.retrofit.ImageDetails;
import boulier.remi.ostmodern.retrofit.RetrofitService;
import boulier.remi.ostmodern.retrofit.RetrofitServiceFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Remi BOULIER on 12/02/2016.
 * email: boulier.r+dev@gmail.com
 */
public class EpisodeWrapper {
    private final Episode mEpisode;

    public EpisodeWrapper(Episode episode) {
        this.mEpisode = episode;
    }

    public interface OnEpisodeUpdate {
        void onComplete(Episode episode);
    }


    public void getImageUrl(final OnEpisodeUpdate callback) {
        RetrofitService service = RetrofitServiceFactory.createRetrofitService(RetrofitService.class, RetrofitService.SERVICE_ENDPOINT);
        if (mEpisode.asImageUrls()) {
            if (mEpisode.getImageDetails() != null) {
                callback.onComplete(mEpisode);
            } else {
                service.getImageDetails(mEpisode.getFirstImageUrl())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ImageDetails>() {
                            @Override
                            public final void onCompleted() {
                                // do nothing
                            }

                            @Override
                            public final void onError(Throwable e) {
                                Log.e("SetRecyclerViewAdapter", e.getMessage());
                            }

                            @Override
                            public final void onNext(ImageDetails response) {
                                Log.d("SetRecyclerViewAdapter", "onNext");
                                if (response != null) {
                                    mEpisode.setImageDetails(response);

                                    callback.onComplete(mEpisode);
                                }
                            }
                        });
            }
        }
    }
}
