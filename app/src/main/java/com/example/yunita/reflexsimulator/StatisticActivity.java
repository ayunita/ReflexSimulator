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

    private GameManager gameManager = new GameManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        setResult(R.id.stats_result1, gameManager.printOutReactionTimerResult(this));
        setResult(R.id.stats_result2, gameManager.printOutGameshowResult(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_statistic, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        finish();
    }

    public void setResult(int id, String gameResult) {
        result = (TextView) findViewById(id);
        result.setText(gameResult);
    }

    public void clearData(MenuItem item) {
        gameManager.clearSavedData(this);
        setResult(R.id.stats_result1, gameManager.printOutReactionTimerResult(this));
        setResult(R.id.stats_result2, gameManager.printOutGameshowResult(this));
    }

    public void emailResult(MenuItem item) {
        String output = gameManager.printOutReactionTimerResult(this)
                + "\n" + gameManager.printOutGameshowResult(this);
        String subject = "Reflex Simulator - Result";
        gameManager.handleEmail(this, subject, output);
    }

}
