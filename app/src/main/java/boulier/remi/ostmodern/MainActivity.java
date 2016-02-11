package boulier.remi.ostmodern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import boulier.remi.ostmodern.retrofit.RetrofitServiceFactory;
import boulier.remi.ostmodern.retrofit.Set;
import boulier.remi.ostmodern.retrofit.SetDeserializer;
import boulier.remi.ostmodern.retrofit.SetService;
import retrofit.converter.GsonConverter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    ArrayList<Set> mSets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Type collectionType = new TypeToken<ArrayList<Set>>() {
        }.getType();
        GsonConverter gsonConverter = RetrofitServiceFactory.getGsonConverter(collectionType, new SetDeserializer());

        SetService service = RetrofitServiceFactory.createRetrofitService(SetService.class, SetService.SERVICE_ENDPOINT, gsonConverter);
        service.getSets()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<Set>>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("MainActivity", e.getMessage());
                    }

                    @Override
                    public final void onNext(ArrayList<Set> response) {

//                            mCardAdapter.addData(response);
//                        mSets.add(response);
                        Log.d("MainActivity", "onNext");
                    }
                });


    }
}
