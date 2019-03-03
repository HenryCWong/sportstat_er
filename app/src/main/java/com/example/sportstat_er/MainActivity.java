package com.example.sportstat_er;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.snapchat.kit.sdk.SnapCreative;
import com.snapchat.kit.sdk.creative.api.SnapCreativeKitApi;
import com.snapchat.kit.sdk.creative.exceptions.SnapMediaSizeException;
import com.snapchat.kit.sdk.creative.media.SnapMediaFactory;
import com.snapchat.kit.sdk.creative.media.SnapPhotoFile;
import com.snapchat.kit.sdk.creative.models.SnapPhotoContent;
import com.snapchat.kit.sdk.creative.media.SnapSticker;
import com.snapchat.kit.sdk.creative.models.SnapLiveCameraContent;
import com.snapchat.kit.sdk.creative.exceptions.SnapStickerSizeException;
import com.snapchat.kit.sdk.creative.media.SnapMediaFactory;



import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Player> playerList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PlayerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SnapCreativeKitApi snapCreativeKitApi = SnapCreative.getApi(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new PlayerAdapter(playerList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Player player = playerList.get(position);
                Toast.makeText(getApplicationContext(), player.getName() + " stats opening in SnapChat!", Toast.LENGTH_SHORT).show();

                snapSend();

                //SnapSticker snapSticker = null;
                //SnapLiveCameraContent snapLiveCameraContent = new SnapLiveCameraContent();


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        preparePlayerData();
    }

    private void snapSend() {
        SnapMediaFactory snapMediaFactory = SnapCreative.getMediaFactory(this);
        SnapSticker snapSticker = null;
        SnapSticker stickerFile = null;
        try {
            snapSticker = snapMediaFactory.getSnapStickerFromFile(stickerFile);
        } catch (SnapStickerSizeException e) {
            return;
        }
        // Height and width~~ ~~in pixels
        snapSticker.setWidth(300);
        snapSticker.setHeight(300);

        // Position is specified as a ratio between 0 & 1 to place the center of the sticker
        snapSticker.setPosX(0.5f);
        snapSticker.setPosY(0.5f);

        // Specify clockwise rotation desired
        snapSticker.setRotationDegreesClockwise(0); // degrees clockwise
        <your-snap-content>.setSnapSticker(snapSticker);
        // Note: Your snap content can be video, photo, or live-camera content
    }

    private void preparePlayerData() {
        Player player = new Player("Harden, James", "Houston Rocket", "PG");
        playerList.add(player);
        player = new Player("Harris, Devin","Dallas Mavericks","PG");
        playerList.add(player);
        player = new Player("Harrison, Shaquille", "Chicago Bulls","PG");
        playerList.add(player);
        player = new Player("Hill, George","Milwaukee Bucks","PG");
        playerList.add(player);
        player = new Player("Holiday, Aaron","Indiana Pacers","PG");
        playerList.add(player);
        player = new Player("Holiday, Jrue","New Orleans Pelicans","PG");
        playerList.add(player);
        player = new Player("Irving, Kyrie","Boston Celtics","PG");
        playerList.add(player);
        player = new Player("Jackson, Frank","New Orleans Pelicans", "PG");
        mAdapter.notifyDataSetChanged();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
