package com.jsonscratch;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class JSONScratch extends ApplicationAdapter {


    private int n1 = 1;

    @Override
    public void create() {
        save();
        load();
    }

    @Override
    public void render() {

    }

    public void save() {
        Data data = new Data();

        data.nNum1 = n1;
        Json json = new Json();
        writeJson("data.json", json.toJson(data));
    }

    //load from data class
    public void load() {
        String save = readJson("data.json");
        if (!save.isEmpty()) {
            Json json = new Json();
            Data data = json.fromJson(Data.class, save);
            n1 = data.nNum1;
            System.out.println(data.nNum1);
        }
    }

    public void writeJson(String sName, String s) {
        FileHandle file = Gdx.files.local(sName);
        file.writeString(com.badlogic.gdx.utils.Base64Coder.encodeString(s), false);
    }

    //read data from json
    public String readJson(String sName) {
        FileHandle file = Gdx.files.local(sName);
        if (file != null && file.exists()) {
            String sData = file.readString();
            if (!sData.isEmpty()) {
                return com.badlogic.gdx.utils.Base64Coder.decodeString(sData);
            }
        }
        return "";
    }

    //class used for json
    public static class Data {
        private int nNum1;
    }
}

