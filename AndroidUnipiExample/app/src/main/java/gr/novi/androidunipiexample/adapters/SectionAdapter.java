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

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.CustomViewHolder> {

    public interface CustomAdapterClickListener {
        void SectionItemViewHolderSelected(SectionModel data);
        void SectionLikeClicked(SectionModel data);
    }

    private List<SectionModel> sectionList;
    private WeakReference mOnClickListener;

    public SectionAdapter(CustomAdapterClickListener itemViewHolderClickListener) {
        sectionList = new ArrayList<>();
        mOnClickListener = new WeakReference(itemViewHolderClickListener);
    }

    public void updateList(List<SectionModel> dataSet) {
        sectionList = dataSet;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.example_item_view_holder, viewGroup, false);
        return new CustomViewHolder(view, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder viewHolder, final int position) {
        viewHolder.bindData(sectionList.get(position));
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView subtitleTextView;
        private final Button likeButton;
        private final WeakReference<CustomAdapterClickListener> mItemViewHolderClickListener;
        private SectionModel data;

        public CustomViewHolder(View view, WeakReference<CustomAdapterClickListener> itemViewHolderClickListener) {
            super(view);
            mItemViewHolderClickListener = itemViewHolderClickListener;
            titleTextView = view.findViewById(R.id.titleTextViewXml);
            subtitleTextView = view.findViewById(R.id.subTitleTextViewXml);
            likeButton = view.findViewById(R.id.likeButton);
            view.setOnClickListener(itemViewClickListener);
            likeButton.setOnClickListener(likeButtonClickListener);
        }

        public void bindData(SectionModel section) {
            data = section;
            titleTextView.setText(section.title);
            subtitleTextView.setText(section.subtitle);
        }

        private final View.OnClickListener itemViewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemViewHolderClickListener.get().SectionItemViewHolderSelected(data);
            }
        };

        private final View.OnClickListener likeButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemViewHolderClickListener.get().SectionLikeClicked(data);
            }
        };
    }
}