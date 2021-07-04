package com.example.demorealm.realm;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class MyMigration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if (oldVersion == 0L) {
            schema.create("AddressRealmObject")
                    .addField("id", String.class)
                    .addField("province", String.class);
        }

        if (oldVersion == 1L) {
            RealmObjectSchema userRealmObject = schema.get("UserRealmObject");
            userRealmObject.addRealmObjectField("id", userRealmObject);
        }
    }
}
