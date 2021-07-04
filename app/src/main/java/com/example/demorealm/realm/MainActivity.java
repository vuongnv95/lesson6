package com.example.demorealm.realm;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demorealm.R;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

class MainActivity extends AppCompatActivity {
    private static final String REALM_NAME = "myOtherRealm.realm";
    private static final String TAG = "MainActivity";
    private Realm myRealm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
        configRealm();
    }

    private void configRealm() {
        Realm.init(this);

        //realm default config
        myRealm =  Realm.getDefaultInstance();

        //custom config
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(REALM_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();


        myRealm = Realm.getInstance(configuration);
        myRealm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                Log.d(TAG, "onChange() called with: realm = [" + realm + "]");
            }
        });


        insertUser();
        //get all user
        getALlUser();

        //get user with name start Ng
        getUserWithUserName("Ng");


    }

    /**
     * insert user to db
     */
    void insertUser() {
        myRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                UserRealmObject user2 = new UserRealmObject("1", "Trần B", "20");
                realm.copyToRealmOrUpdate(user2);
            }
        });
    }

    /**
     * update user
     */
    void updateUser() {
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                UserRealmObject userRealmObject =
                        realm.where(UserRealmObject.class).equalTo("age", "20").findFirst();
                userRealmObject.setUserAge("18");
            }
        });
    }

    /**
     * get all user in db
     */
    void getALlUser() {
        RealmResults<UserRealmObject> results =
                myRealm.where(UserRealmObject.class).findAll();
        Log.d(TAG, "getALlUser() called${results1.size}");
    }

    /**
     * get user with userName begin ...
     */
    void getUserWithUserName(String userName) {
        RealmResults<UserRealmObject> results1 =
                myRealm.where(UserRealmObject.class).beginsWith("userName", userName).findAll();
        Log.d(TAG, "getUserWithUserName() called with: userName = $userName");
    }

    /**
     * remove item with id
     */
    void removeUser() {
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<UserRealmObject> result =
                        realm.where(UserRealmObject.class).equalTo("id", "1").findAll();
                result.deleteAllFromRealm();
            }
        });
    }

    @Override
    protected void onStop() {
        myRealm.close();
        super.onStop();
    }
}
