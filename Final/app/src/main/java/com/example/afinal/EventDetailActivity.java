package com.example.afinal;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by yu  on 2018/6/16.
 */

public class EventDetailActivity extends AppCompatActivity {

    private ImageView eventDetailImage;
//    private FloatingActionButton fab;
    private TextView title;
    private TextView location;
    private TextView host;
    private TextView url;
    private TextView date;
    private TextView content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        eventDetailImage = (ImageView) findViewById(R.id.event_detail_image);
        title = (TextView) findViewById(R.id.event_detail_title);
        location = (TextView) findViewById(R.id.event_detail_location);
        host = (TextView) findViewById(R.id.event_detail_host);
        url = (TextView) findViewById(R.id.event_detail_url);
        date = (TextView) findViewById(R.id.event_detail_date);
        content = (TextView) findViewById(R.id.event_detail_content);
        setData();
    }

    private void deleteElement() {
        location.setVisibility(View.GONE);
        host.setVisibility(View.GONE);
        url.setVisibility(View.GONE);
        date.setVisibility(View.GONE);
        content.setVisibility(View.GONE);
    }

    private void setData() {
        EventInfo event = getIntent().getParcelableExtra("detail");
        String titleText = event.getTitle();
        event.getImage(getBaseContext()).into(eventDetailImage);
        title.setText(titleText);
        location.setText(String.format("%s%s", "地點：", event.getLocation()));
        host.setText(String.format("%s%s", "主辦單位：", event.getHost()));
        url.setText(String.format("%s%s", "網址：", event.getUrl()));
        date.setText(String.format("%s%s ~ %s", "日期：", event.getStartDate(), event.getEndDate()));
        content.setText(String.format("%s%s", "詳細內容：", event.getContent()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
