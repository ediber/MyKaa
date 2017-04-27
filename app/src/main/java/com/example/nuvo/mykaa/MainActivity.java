package com.example.nuvo.mykaa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.kaaproject.kaa.client.notification.NotificationListener;
import org.kaaproject.kaa.client.notification.NotificationTopicListListener;
import org.kaaproject.kaa.common.endpoint.gen.Topic;
import org.kaaproject.kaa.schema.system.EmptyData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.start)
    View start;
    @BindView(R.id.terminate)
    View terminate;
    @BindView(R.id.getTopics)
    View getTopics;
    @BindView(R.id.subscribeTopic)
    View subscribeTopic;
    @BindView(R.id.unsubscribeTopic)
    View unsubscribeTopic;

    private KaaManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        manager = new KaaManager(MainActivity.this);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                manager.start(new NotificationListener() {
                                  @Override
                                  public void onNotification(long l, EmptyData emptyData) {

                                  }
                              },
                        new NotificationTopicListListener() {
                            @Override
                            public void onListUpdated(List<Topic> list) {

                            }
                        });

/*                // 1
                KaaClient client = Kaa.newClient(new AndroidKaaPlatformContext(this), new SimpleKaaClientStateListener(), true);

                // 2
        ExecutorContext executor = new SimpleExecutorContext(
                CUSTOM_MAX_LIFE_CYCLE_THREADS, CUSTOM_MAX_API_THREADS,
                CUSTOM_MAX_CALLBACK_THREADS, CUSTOM_MIN_SCHEDULED_THREADS
        );

                ExecutorContext executor = new SimpleExecutorContext();

                client.addNotificationListener(new NotificationListener() {
                    @Override
                    public void onNotification(long l, EmptyData emptyData) {

                    }
                });

                client.addTopicListListener(new NotificationTopicListListener() {
                    @Override
                    public void onListUpdated(List<Topic> list) {

                    }
                });

                client.start();*/
            }
        });

        terminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.onTerminate();
            }
        });

        getTopics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.getTopics();
            }
        });

        subscribeTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.subscribeTopic(1);
            }
        });

        unsubscribeTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.unsubscribeTopic(1);
            }
        });

    }
}
