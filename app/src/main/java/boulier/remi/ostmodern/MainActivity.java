package boulier.remi.ostmodern;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import boulier.remi.ostmodern.model.SetSection;
import boulier.remi.ostmodern.retrofit.Episode;
import boulier.remi.ostmodern.retrofit.RetrofitService;
import boulier.remi.ostmodern.retrofit.RetrofitServiceFactory;
import boulier.remi.ostmodern.retrofit.Set;
import boulier.remi.ostmodern.retrofit.SetDeserializer;
import retrofit.converter.GsonConverter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final static int MAX_EPISODES_IN_LINE = 3;

    private SetRecyclerViewAdapter mAdapter;
    private RetrofitService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "onCreate");
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sets_recycler_view);
        GridLayoutManager gridManager = new GridLayoutManager(this, MAX_EPISODES_IN_LINE);
        recyclerView.setLayoutManager(gridManager);
        mAdapter = new SetRecyclerViewAdapter(new SetRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Episode episode) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra(DetailsActivity.INTENT_CONTENT_EPISODE, episode);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);

        gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mAdapter.getItemViewType(position) == SetRecyclerViewAdapter.TYPE_ITEM_EPISODE ? 1 : MAX_EPISODES_IN_LINE;
            }
        });

        Type collectionType = new TypeToken<ArrayList<Set>>() {
        }.getType();
        GsonConverter gsonConverter = RetrofitServiceFactory.getGsonConverter(collectionType, new SetDeserializer());
        mService = RetrofitServiceFactory.createRetrofitService(RetrofitService.class, RetrofitService.SERVICE_ENDPOINT, gsonConverter);

        getSets();
    }

    private void getSets() {
        mService.getSets()
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
                        displaySnackBar();
                    }

                    @Override
                    public final void onNext(ArrayList<Set> response) {
                        Log.d("MainActivity", "onNext");
                        mAdapter.setList(createAdapterList(response));
                    }
                });
    }

    private void displaySnackBar() {
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), R.string.error_loading, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.this.getSets();
                    }
                });

        snackbar.show();
    }

    private List<Object> createAdapterList(List<Set> sets) {
        List<Object> adapterList = new ArrayList<>();
        for (Set set : sets) {
            adapterList.add(new SetSection(set));
            adapterList.addAll(set.getItems());
        }

        return adapterList;
    }
}
