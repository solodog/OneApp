package util;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import models.Usermodel;

/**
 * Created by 17524 on 2019/10/23.
 */

public class Realmutil  {
    private Realm mrealm;
    private Context mContext;
    public Realmutil(){
        mrealm=Realm.getDefaultInstance();
    }
    public void close(){
        if(mrealm!=null||mrealm.isClosed()){
            mrealm.close();
        }
    }
    public void savedata(Usermodel usermodel){
        mrealm.beginTransaction();
        mrealm.insertOrUpdate(usermodel);
        mrealm.commitTransaction();
    }

    public List<Usermodel> getalluser(){
        String User="";
        RealmQuery<Usermodel> query=mrealm.where(Usermodel.class);
        RealmResults<Usermodel> results=query.findAll();
        return results;
    }
    public boolean yzi(String phone,String passwrod){
        RealmQuery<Usermodel> query=mrealm.where(Usermodel.class);
        query=query.equalTo("phone",phone).equalTo("password",passwrod);
        Usermodel usermodel=query.findFirst();
        if(usermodel!=null){
            return true;
        }
        return false;
    }
    public  void xg(Usermodel usermodel){
        savedata(usermodel);
    }
}
