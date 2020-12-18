package gr.novi.androidunipiexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SectionActivity extends AppCompatActivity {

    public static final String SectionActivityExtrasKey = "SectionActivityExtras";

    private TextView mSectionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        mSectionId = findViewById(R.id.sectionIdXml);
        Integer sectionId = getIntent().getExtras().getInt(SectionActivityExtrasKey);
        mSectionId.setText(String.valueOf(sectionId));
    }
}