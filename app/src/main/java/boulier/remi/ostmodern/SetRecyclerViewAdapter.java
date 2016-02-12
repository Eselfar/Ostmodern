package boulier.remi.ostmodern;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import boulier.remi.ostmodern.model.EpisodeWrapper;
import boulier.remi.ostmodern.model.ItemWrapper;
import boulier.remi.ostmodern.model.SetSection;
import boulier.remi.ostmodern.model.SetSectionWrapper;
import boulier.remi.ostmodern.retrofit.Episode;
import boulier.remi.ostmodern.retrofit.Item;

/**
 * Created by Remi BOULIER on 11/02/2016.
 * email: boulier.r+dev@gmail.com
 */
public class SetRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ArrayList<Object> mList = new ArrayList<>();
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
            bindSetViewHolder((SetViewHolder) holder, (SetSection) listItem);
        } else if (holder instanceof EpisodeViewHolder) {
            bindEpisodeViewHolder((EpisodeViewHolder) holder, (Item) listItem);
        } else {
            ((DividerViewHolder) holder).dividerHeading.setText(((Item) listItem).getHeading());
        }
    }

    private void bindSetViewHolder(final SetViewHolder setViewHolder, final SetSection setSection) {
        setViewHolder.title.setText(setSection.getTitle());
        setViewHolder.summary.setText(setSection.getSummary());
        setViewHolder.image.setImageResource(R.drawable.no_image_200px);

        SetSectionWrapper wrapper = new SetSectionWrapper(setSection);
        wrapper.getImageUrl(new SetSectionWrapper.OnSetSectionUpdate() {
            @Override
            public void onComplete(SetSection setSection) {
                Context context = setViewHolder.itemView.getContext();
                Picasso.with(context)
                        .load(setSection.getImageDetails().getUrl())
                        .placeholder(ContextCompat.getDrawable(context, R.drawable.no_image_200px))
                        .error(ContextCompat.getDrawable(context, R.drawable.no_image_200px))
                        .into(setViewHolder.image);
            }
        });
    }

    private void bindEpisodeViewHolder(final EpisodeViewHolder episodeViewHolder, final Item item) {
        episodeViewHolder.title.setText("");
        episodeViewHolder.image.setImageResource(R.drawable.no_image_200px);

        ItemWrapper wrapper = new ItemWrapper(item);
        wrapper.getEpisodeInfo(new ItemWrapper.OnEpisodeInfoUpdate() {
            @Override
            public void onComplete(Item item) {
                episodeViewHolder.title.setText(item.getEpisode().getTitle());
                episodeViewHolder.bind(item.getEpisode(), mListener);

                EpisodeWrapper episodeWrapper = new EpisodeWrapper(item.getEpisode());
                episodeWrapper.getImageUrl(new EpisodeWrapper.OnEpisodeUpdate() {
                    @Override
                    public void onComplete(Episode episode) {
                        Context context = episodeViewHolder.itemView.getContext();
                        Picasso.with(context)
                                .load(episode.getImageDetails().getUrl())
                                .placeholder(ContextCompat.getDrawable(context, R.drawable.no_image_200px))
                                .error(ContextCompat.getDrawable(context, R.drawable.no_image_200px))
                                .into(episodeViewHolder.image);
                    }
                });
            }
        });
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

    /* ViewHolders */

    public static class SetViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView summary;
        final ImageView image;

        public SetViewHolder(View setView) {
            super(setView);
            title = (TextView) setView.findViewById(R.id.set_title);
            summary = (TextView) setView.findViewById(R.id.set_summary);
            image = (ImageView) setView.findViewById(R.id.set_image);
        }
    }

    public static class EpisodeViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final ImageView image;

        public EpisodeViewHolder(View setView) {
            super(setView);
            title = (TextView) setView.findViewById(R.id.episode_title);
            image = (ImageView) setView.findViewById(R.id.episode_image);
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
        final TextView dividerHeading;

        public DividerViewHolder(View setView) {
            super(setView);
            dividerHeading = (TextView) setView.findViewById(R.id.divider_header);
        }
    }
}
