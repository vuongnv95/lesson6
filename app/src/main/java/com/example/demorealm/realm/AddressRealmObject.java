package com.example.demorealm.realm;

import io.realm.RealmObject;

public class AddressRealmObject extends RealmObject {
    private String province;

   public AddressRealmObject() {
   }

   public AddressRealmObject(String province) {
      this.province = province;
   }

   public String getProvince() {
      return province;
   }

   public void setProvince(String province) {
      this.province = province;
   }
}
