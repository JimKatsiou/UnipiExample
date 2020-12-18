package gr.novi.androidunipiexample.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import gr.novi.androidunipiexample.R;
import gr.novi.androidunipiexample.models.forummodels.SectionModel;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    public interface TopicAdapterClickListener {
        void TopicItemViewHolderSelected(SectionModel data);
    }

    private List<SectionModel> sectionList;
    private WeakReference mOnClickListener;

    public TopicAdapter(TopicAdapterClickListener itemViewHolderClickListener) {
        sectionList = new ArrayList<>();
        mOnClickListener = new WeakReference(itemViewHolderClickListener);
    }

    public void updateList(List<SectionModel> dataSet) {
        sectionList = dataSet;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.topic_item_view_holder, viewGroup, false);
        return new TopicViewHolder(view, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(TopicViewHolder viewHolder, final int position) {
        viewHolder.bindData(sectionList.get(position));
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }


    public static class TopicViewHolder extends RecyclerView.ViewHolder {
        private final TextView topicTitleTextView;
        private final WeakReference<TopicAdapterClickListener> mItemViewHolderClickListener;
        private SectionModel data;

        public TopicViewHolder(View view, WeakReference<TopicAdapterClickListener> itemViewHolderClickListener) {
            super(view);
            mItemViewHolderClickListener = itemViewHolderClickListener;
            topicTitleTextView = view.findViewById(R.id.topicTitleXml);
            view.setOnClickListener(itemViewClickListener);
        }

        public void bindData(SectionModel section) {
            data = section;
            topicTitleTextView.setText(section.title);
        }

        private final View.OnClickListener itemViewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemViewHolderClickListener.get().TopicItemViewHolderSelected(data);
            }
        };
    }
}