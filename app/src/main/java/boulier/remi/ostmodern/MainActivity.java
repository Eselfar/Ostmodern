package boulier.remi.ostmodern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sets_recycler_view);
        GridLayoutManager gridManager = new GridLayoutManager(this, MAX_EPISODES_IN_LINE);
        recyclerView.setLayoutManager(gridManager);
        final SetRecyclerViewAdapter adapter = new SetRecyclerViewAdapter(new SetRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Episode episode) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra(DetailsActivity.INTENT_CONTENT_EPISODE, episode);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getItemViewType(position) == SetRecyclerViewAdapter.TYPE_ITEM_EPISODE ? 1 : MAX_EPISODES_IN_LINE;
            }
        });


        Type collectionType = new TypeToken<ArrayList<Set>>() {
        }.getType();
        GsonConverter gsonConverter = RetrofitServiceFactory.getGsonConverter(collectionType, new SetDeserializer());

        RetrofitService service = RetrofitServiceFactory.createRetrofitService(RetrofitService.class, RetrofitService.SERVICE_ENDPOINT, gsonConverter);
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
                        Log.d("MainActivity", "onNext");
                        adapter.setList(createAdapterList(response));
                    }
                });
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
