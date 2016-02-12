package boulier.remi.ostmodern.model;

import android.util.Log;

import boulier.remi.ostmodern.retrofit.Episode;
import boulier.remi.ostmodern.retrofit.Item;
import boulier.remi.ostmodern.retrofit.RetrofitService;
import boulier.remi.ostmodern.retrofit.RetrofitServiceFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Remi BOULIER on 12/02/2016.
 * email: boulier.r+dev@gmail.com
 */
public class ItemWrapper {
    private final Item mItem;

    public ItemWrapper(Item item) {
        mItem = item;
    }

    public interface OnEpisodeInfoUpdate {
        void onComplete(Item item);
    }

    public void getEpisodeInfo(final OnEpisodeInfoUpdate callback) {

        // if we already have the information on the episode
        if (mItem.getEpisode() != null) {
            callback.onComplete(mItem);
        } else {
            // If we don't have the information on the episode, we call the server.
            RetrofitService service = RetrofitServiceFactory.createRetrofitService(RetrofitService.class, RetrofitService.SERVICE_ENDPOINT);
            service.getEpisode(mItem.getContentUrl())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Episode>() {
                        @Override
                        public final void onCompleted() {
                            // do nothing
                        }

                        @Override
                        public final void onError(Throwable e) {
                            Log.e("SetRecyclerViewAdapter", e.getMessage());
                        }

                        @Override
                        public final void onNext(Episode response) {
                            Log.d("SetRecyclerViewAdapter", "onNext");
                            if (response != null) {
                                mItem.setEpisode(response);
                                callback.onComplete(mItem);
                            }
                        }
                    });
        }
    }
}
