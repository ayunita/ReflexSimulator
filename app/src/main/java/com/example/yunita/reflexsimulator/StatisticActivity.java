/*
Statistic shows the maximum, minimum, mean, and
median user's reflex and players' buzzer counts.

Copyright (C) 2015  Andriani Yunita

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.yunita.reflexsimulator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;


public class StatisticActivity extends AppCompatActivity {

    private TextView result;

    private Statistic stat = new Statistic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        result = (TextView) findViewById(R.id.stats_result2);

        setResult(R.id.stats_result1, stat.getReactionTimerResult(this));
        setResult(R.id.stats_result2, stat.getGameshowResult(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_statistic, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up custom_reflex_button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    public void setResult(int id, String gameResult) {
        result = (TextView) findViewById(id);
        result.setText(gameResult);
    }

    public void clearData(MenuItem item) {
        stat.clearStatistic(this);
        setResult(R.id.stats_result1, stat.getReactionTimerResult(this));
        setResult(R.id.stats_result2, stat.getGameshowResult(this));
    }

    public void sendToEmail(MenuItem item) {
        // CODE HERE
    }


}
