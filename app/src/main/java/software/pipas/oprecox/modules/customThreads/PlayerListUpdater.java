package software.pipas.oprecox.modules.customThreads;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

import software.pipas.oprecox.modules.dataType.Player;

import software.pipas.oprecox.R;
import software.pipas.oprecox.modules.interfaces.OnPlayerImageLoader;

/**
 * Created by nuno_ on 16-Aug-17.
 */

public class PlayerListUpdater extends Thread
{
    private OnPlayerImageLoader onPlayerImageLoader;
    private Context context;
    private ArrayList<Player> players;
    private boolean closed;

    private int timeBetweenRefresh;
    private int timeOfPlayerExpired;


    public PlayerListUpdater(Context context, OnPlayerImageLoader onPlayerImageLoader, ArrayList<Player> players)
    {
        this.onPlayerImageLoader = onPlayerImageLoader;
        this.context = context;
        this.players = players;
        this.closed = false;
        this.timeBetweenRefresh = this.context.getResources().getInteger(R.integer.TIME_BETWEEN_UPDATES);
        this.timeOfPlayerExpired = this.context.getResources().getInteger(R.integer.TIME_LIMIT_FOR_PLAYER_LIST_REFRESH);

    }

    @Override
    public void run()
    {
        while(!this.closed)
        {
            if(this.update()) {
                onPlayerImageLoader.onRefreshUI();}
            this.sleep();
        }
    }


    public void close()
    {
        this.closed = true;
    }


    private boolean update()
    {
        boolean update = false;
        ArrayList<Integer> indexes = new ArrayList<>();

        for (Player player : this.players)
        {
            int index = this.players.indexOf(player);
            long timeAnnounced = player.getTimeAnnounced();
            long diff = System.currentTimeMillis() - timeAnnounced;

            if (diff >= this.timeOfPlayerExpired)
            {
                indexes.add(index);
                update = true;
            }
        }

        Collections.reverse(indexes);

        if(update)
        {
            for(int index : indexes)
            {
                this.players.remove(index);
            }
        }

        return update;
    }

    private void sleep()
    {
        try {Thread.sleep(this.timeBetweenRefresh);}
        catch (InterruptedException e) {e.printStackTrace();}
    }



}
