package gr.novi.androidunipiexample;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gr.novi.androidunipiexample.adapters.SectionAdapter;
import gr.novi.androidunipiexample.adapters.TopicAdapter;
import gr.novi.androidunipiexample.models.forummodels.SectionModel;

public class ForumsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView mTopicRecyclerView;
    private SectionAdapter mAdapter;
    private TopicAdapter mTopicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forums);
        mRecyclerView = findViewById(R.id.recyclerView);
        mTopicRecyclerView = findViewById(R.id.topicRecycler);
        mAdapter = new SectionAdapter(mSectionSelectedClickListener);
        mTopicAdapter = new TopicAdapter(mTopicSelectedClickListener);

        mRecyclerView.setAdapter(mAdapter);
        mTopicRecyclerView.setAdapter(mTopicAdapter);

        ArrayList<SectionModel> sectionList = new ArrayList<>(1000);
        for (int number = 1; number < 1000; number++) {
            SectionModel singleSectionModel = new SectionModel("Title " + number, "SubTitle " + number, number);
            sectionList.add(singleSectionModel);
        }
        mAdapter.updateList(sectionList);
        mAdapter.notifyDataSetChanged();

        mTopicAdapter.updateList(sectionList);
        mTopicAdapter.notifyDataSetChanged();
    }

    private void startSectionWith(SectionModel section) {
        Intent intent = new Intent(this, SectionActivity.class);
        intent.putExtra(SectionActivity.SectionActivityExtrasKey, section.id);
        startActivity(intent);
    }

    private void handleTopic(SectionModel topic) {
        //TODO handling also create a new TopicModel
        ArrayList<SectionModel> sectionList = new ArrayList<>(10);
        for (int number = 1; number < 10; number++) {
            SectionModel singleSectionModel = new SectionModel("Title " + number, "SubTitle " + number, number);
            sectionList.add(singleSectionModel);
        }
        mRecyclerView.smoothScrollBy(0,0);
        mAdapter.updateList(sectionList);
        mAdapter.notifyDataSetChanged();
    }

    private final SectionAdapter.CustomAdapterClickListener mSectionSelectedClickListener = new SectionAdapter.CustomAdapterClickListener() {

        @Override
        public void SectionItemViewHolderSelected(SectionModel data) {
            startSectionWith(data);
        }

        @Override
        public void SectionLikeClicked(SectionModel data) {
            //TODO like handling
            SectionModel test = data;
        }
    };

    private final TopicAdapter.TopicAdapterClickListener mTopicSelectedClickListener = new TopicAdapter.TopicAdapterClickListener() {

        @Override
        public void TopicItemViewHolderSelected(SectionModel data) {
            handleTopic(data);
        }
    };
}