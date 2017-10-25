package bbit3a.com.lab3_sqlite;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Games> listOfGames;
    private ListView list;
    private DatabaseHandler db;

    BaseAdapter myAdapter = new BaseAdapter() {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView != null)
                return convertView;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.row_layout, parent, false);
            TextView gamerTV = (TextView) rowView.findViewById(R.id.tv_gamer);
            TextView gamesTV = (TextView) rowView.findViewById(R.id.tv_games);

            gamerTV.setText(db.getGamer(listOfGames.get(position).getBelongTo()).getUserName());
            gamesTV.setText(listOfGames.get(position).getCurrentGames());

            return rowView;
        }
        @Override
        public int getCount() {
            return listOfGames.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);
        List<Gamers> gamers = db.getAllGamers();
        if (gamers.size() == 0) {
            db.addGamer(new Gamers("skyZuri"));
            db.addGamer(new Gamers("ian123"));
            db.addGamer(new Gamers("plate"));
            db.addGamer(new Gamers("cup"));
            gamers = db.getAllGamers();
        }

        Log.i("Gamers", gamers.toString());
        listOfGames = db.getAllGames();
        if (listOfGames.size() == 0) {
            db.addGames(new Games(gamers.get(0).getGamerID(), "Halo"));
            db.addGames(new Games(gamers.get(1).getGamerID(), "Bully"));
            db.addGames(new Games(gamers.get(2).getGamerID(), "GTA"));
            db.addGames(new Games(gamers.get(3).getGamerID(), "Fifa"));

            listOfGames = db.getAllGamesWithGamers(gamers.get(0).getGamerID());
        } else {
            listOfGames = db.getAllGamesWithGamers(gamers.get(0).getGamerID());
        }
        Log.i("Gamers", listOfGames.toString());

        list = (ListView) findViewById(R.id.listView1);
        list.setAdapter(myAdapter);
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

