package boulier.remi.ostmodern.model;

import android.util.Log;

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
public class SetSectionWrapper {
    private final SetSection mSetSection;

    public SetSectionWrapper(SetSection setSection) {
        this.mSetSection = setSection;
    }

    public interface OnSetSectionUpdate {
        void onComplete(SetSection setSection);
    }

    public void getImageUrl(final OnSetSectionUpdate callback) {
        RetrofitService service = RetrofitServiceFactory.createRetrofitService(RetrofitService.class, RetrofitService.SERVICE_ENDPOINT);
        if (mSetSection.asImageUrls()) {
            if (mSetSection.getImageDetails() != null) {
                callback.onComplete(mSetSection);
            } else {
                service.getImageDetails(mSetSection.getFirstImageUrl())
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
                                    mSetSection.setImageDetails(response);

                                    callback.onComplete(mSetSection);
                                }
                            }
                        });
            }
        }
    }
}
