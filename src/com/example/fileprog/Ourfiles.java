package com.example.fileprog;


public class Ourfiles {

    String filenames;
    String filecontent;

    public Ourfiles(String filenames, String filecontent) {
        this.filenames = filenames;
        this.filecontent = filecontent;
    }

    public String getFilenames() {
        return filenames;
    }

    public String getFilecontent() {
        return filecontent;
    }
}
