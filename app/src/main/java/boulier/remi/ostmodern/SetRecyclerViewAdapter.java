package boulier.remi.ostmodern;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import boulier.remi.ostmodern.model.SetSection;
import boulier.remi.ostmodern.retrofit.Episode;
import boulier.remi.ostmodern.retrofit.Item;
import boulier.remi.ostmodern.retrofit.RetrofitService;
import boulier.remi.ostmodern.retrofit.RetrofitServiceFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Remi BOULIER on 11/02/2016.
 * email: boulier.r+dev@gmail.com
 */
public class SetRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Object> mList = new ArrayList<>();
    private final OnItemClickListener mListener;

    public final static int TYPE_SET = 0;
    public final static int TYPE_ITEM_DIVIDER = 1;
    public final static int TYPE_ITEM_EPISODE = 2;

    public SetRecyclerViewAdapter(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Episode episode);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_SET) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_view, parent, false);
            return new SetViewHolder(view);
        } else if (viewType == TYPE_ITEM_EPISODE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_view, parent, false);
            return new EpisodeViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.divider_view, parent, false);
            return new DividerViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Object listItem = mList.get(position);

        if (holder instanceof SetViewHolder) {
            ((SetViewHolder) holder).title.setText(((SetSection) listItem).getTitle());
            ((SetViewHolder) holder).summary.setText(((SetSection) listItem).getSummary());

        } else if (holder instanceof EpisodeViewHolder) {
            final Item item = (Item) listItem;
            final EpisodeViewHolder episodeViewHolder = (EpisodeViewHolder) holder;
            episodeViewHolder.episodeImage.setImageResource(R.drawable.no_image_200px);

            // if we already have the information on the episode
            if (item.getEpisode() != null) {
                episodeViewHolder.episodeTitle.setText(item.getEpisode().getTitle());
                episodeViewHolder.bind(item.getEpisode(), mListener);
            } else {
                // If we don't have the information on the episode, we call the server.
                RetrofitService service = RetrofitServiceFactory.createRetrofitService(RetrofitService.class, RetrofitService.SERVICE_ENDPOINT);
                service.getEpisode(item.getContentUrl())
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
                                item.setEpisode(response);

                                episodeViewHolder.episodeTitle.setText(response.getTitle());
                                episodeViewHolder.bind(response, mListener);

                                if (response.getImageUrls() != null && response.getImageUrls().size() > 0) {
                                    Log.d("SetRecyclerViewAdapter", response.getImageUrls().get(0));
                                } else
                                    Log.d("SetRecyclerViewAdapter", "No image url");
                            }
                        });
            }

        } else {
            ((DividerViewHolder) holder).dividerHeading.setText(((Item) listItem).getHeading());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<Object> list) {
        mList.clear();
        if (list != null) {
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = mList.get(position);
        if (obj instanceof SetSection) {
            return TYPE_SET;
        } else if (((Item) obj).getContentType() == Item.Type.EPISODE) {
            return TYPE_ITEM_EPISODE;
        } else {
            return TYPE_ITEM_DIVIDER;
        }
    }

    public static class SetViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView summary;

        public SetViewHolder(View setView) {
            super(setView);
            title = (TextView) setView.findViewById(R.id.set_title);
            summary = (TextView) setView.findViewById(R.id.set_summary);
        }
    }

    public static class EpisodeViewHolder extends RecyclerView.ViewHolder {
        TextView episodeTitle;
        ImageView episodeImage;

        public EpisodeViewHolder(View setView) {
            super(setView);
            episodeTitle = (TextView) setView.findViewById(R.id.episode_title);
            episodeImage = (ImageView) setView.findViewById(R.id.episode_image);
        }

        public void bind(final Episode episode, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(episode);
                }
            });
        }
    }

    public static class DividerViewHolder extends RecyclerView.ViewHolder {
        TextView dividerHeading;

        public DividerViewHolder(View setView) {
            super(setView);
            dividerHeading = (TextView) setView.findViewById(R.id.divider_header);
        }
    }
}
