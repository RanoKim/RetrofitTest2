package com.ticcorp.retrofittest2;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.ticcorp.retrofittest2.core.ItemController;
import com.ticcorp.retrofittest2.core.LoginController;
import com.ticcorp.retrofittest2.core.MyScoreController;
import com.ticcorp.retrofittest2.model.CustomPreference;
import com.ticcorp.retrofittest2.model.DBManager;
import com.ticcorp.retrofittest2.model.StaticSQLite;
import com.ticcorp.retrofittest2.module.ServerAccessModule;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SQLiteTestActivity extends Activity {

    @Bind(R.id.btn_login)
    Button loginBtn;

    @Bind(R.id.btn_logout)
    Button logoutBtn;

    @Bind(R.id.btn_select)
    Button selectBtn;

    @Bind(R.id.btn_insert)
    Button insertBtn;

    @Bind(R.id.btn_update)
    Button updateBtn;

    @Bind(R.id.btn_delete)
    Button deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_test);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    void loginBtn() {

        String userId = "5555";
        String name = "박태호";
        int platform = 1;
        ServerAccessModule.getInstance().login(this.getApplicationContext(), userId, name, platform);

    }

    @OnClick(R.id.btn_logout)
    void logoutBtn() {
        DBManager db = new DBManager(this.getApplicationContext(), StaticSQLite.TICSONG_DB, null, 1);
        logoutTest("4444");
        db.drop();

    }

    @OnClick(R.id.btn_select)
    void selectBtnClicked() {
        //retrieveItemTest("2222");
        //retrieveFriendsSocre("2222");
        CustomPreference customPreference = CustomPreference.getInstance(this.getApplicationContext());
        DBManager db = new DBManager(this.getApplicationContext(), StaticSQLite.TICSONG_DB, null, 1 );
        Cursor cursor = null;
        cursor = db.retrieve(StaticSQLite.retrieveMyScoreSQL(customPreference.getValue("userId", "userId")));

        while(cursor.moveToNext()) {
            Log.e("Login후 MyScore", customPreference.getValue("userId", "userId") + " / " + cursor.getInt(1) + " / " + cursor.getInt(2) );
        }

        cursor = db.retrieve(StaticSQLite.retrieveItemSQL(customPreference.getValue("userId", "userId")));
        while(cursor.moveToNext()) {

            Log.e("Login후 ITEM", customPreference.getValue("userId", "userId") + " / " +  cursor.getInt(1) + " / " +  cursor.getInt(2) +
                    " / " +  cursor.getInt(3) + " / " + cursor.getInt(4) );
        }
        cursor.close();
        db.close();
    }
    @OnClick(R.id.btn_insert)
    void insertBtnClicked() {
        //retrieveMyScoreTest("2222");
        //registerTest("3333", "김대섭", 2);
        //loginTest("3333", "김대섭", 2);
    }
    @OnClick(R.id.btn_update)
    void updateBtnClicked() {

    }
    @OnClick(R.id.btn_delete)
    void deleteBtnClicked() {

        DBManager db = new DBManager(this.getApplicationContext(), StaticSQLite.TICSONG_DB, null, 1 );
        Cursor cursor = null;

        cursor = db.retrieve(StaticSQLite.retrieveMyScoreSQL("4444"));
        while(cursor.moveToNext()) {
            Log.e("Login후 MyScore", "4444" + cursor.getInt(1) + cursor.getInt(2) );
        }

        cursor = db.retrieve(StaticSQLite.retrieveItemSQL("4444"));
        while(cursor.moveToNext()) {

            Log.e("Login후 ITEM", "4444" + cursor.getInt(1) + cursor.getInt(2) + cursor.getInt(3)+
                    cursor.getInt(4) );
        }

        db.close();


    }

    private void loginTest(String userId, String name, int platform) {
        LoginController loginCon = LoginController.getInstance();
        loginCon.requestLogin(this, userId, name, ""+platform);
    }


    private void logoutTest(String userId) {

        DBManager db = new DBManager(this.getApplicationContext(), StaticSQLite.TICSONG_DB, null, 1);
        Cursor cursor = null;

        cursor = db.retrieve(StaticSQLite.retrieveMyScoreSQL(userId));
        while(cursor.moveToNext()) {
            updateMyScoreTest(userId, cursor.getInt(1), cursor.getInt(2));
        }

        cursor = db.retrieve(StaticSQLite.retrieveItemSQL(userId));
        while(cursor.moveToNext()) {
            updateItemTest(userId,
                cursor.getInt(1),
                cursor.getInt(2),
                cursor.getInt(3),
                cursor.getInt(4)
            );
        }
        cursor.close();
        db.close();
    }

    private void gameFinished(String userId, int exp, int userLevel, int item1Cnt, int item2Cnt, int item3Cnt, int item4Cnt) {

        DBManager db = new DBManager(this.getApplicationContext(), StaticSQLite.TICSONG_DB, null, 1);
        Cursor cursor = null;

        // SQLite에 MyScore, Item Update
        db.insert(StaticSQLite.updateMyScoreSQL(userId, exp, userLevel));
        db.insert(StaticSQLite.updateItemSQL(userId, item1Cnt, item2Cnt, item3Cnt, item4Cnt));

        // SQLite에서 MyScore, Item 가져와서
        // 서버에 Update
        cursor = db.retrieve(StaticSQLite.retrieveMyScoreSQL(userId));
        while(cursor.moveToNext()) {
            Log.e("Get MYSCORE from SQLite", ""+cursor.getInt(1)+cursor.getInt(2));
            updateMyScoreTest(userId, cursor.getInt(1), cursor.getInt(2));
        }

        cursor = db.retrieve(StaticSQLite.retrieveItemSQL(userId));
        while(cursor.moveToNext()) {
            Log.e("Get ITEM from SQLite", ""+cursor.getInt(1)+cursor.getInt(2)+cursor.getInt(3)+cursor.getInt(4));
            updateItemTest(userId,
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4)
            );
        }
        cursor.close();
        db.close();
    }


    private void insertMyScoreTest(String userId, int exp, int userLevel) {
        MyScoreController scoreCon = MyScoreController.getInstance();
        scoreCon.insertMyScore(this, userId, exp, userLevel);
    }
    private void updateMyScoreTest(String userId, int exp, int userLevel) {
        MyScoreController scoreCon = MyScoreController.getInstance();
        scoreCon.updateMyScore(this, userId, exp, userLevel);
    }
    private void retrieveMyScoreTest(String userId) {
        MyScoreController scoreCon = MyScoreController.getInstance();
        scoreCon.getMyScore(this, userId);
    }
    private void retrieveScores(String userId) {
        MyScoreController scoreCon = MyScoreController.getInstance();
        scoreCon.getScores(this, userId);
    }

   /* private void retrieveFriendsSocre(String userId) {
        MyScoreController scoreCon = MyScoreController.getInstance();
        scoreCon.getFriendsScore(this, userId);
    }*/

    private void insertItemTest(String userId, int item1Cnt, int item2Cnt, int item3Cnt, int item4Cnt) {
        ItemController itemCon = ItemController.getInstance();
        itemCon.insertItem(this, userId, item1Cnt, item2Cnt, item3Cnt, item4Cnt);
    }
    private void updateItemTest(String userId, int item1Cnt, int item2Cnt, int item3Cnt, int item4Cnt) {
        ItemController itemCon = ItemController.getInstance();
        itemCon.updateItem(this, userId, item1Cnt, item2Cnt, item3Cnt, item4Cnt);
    }
    private void retrieveItemTest(String userId) {
        ItemController itemCon = ItemController.getInstance();
        itemCon.getItem(this, userId);
    }

}

