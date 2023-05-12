package com.example.diccionariobroranterraba;

public class ImageAndSoundModel {
    private int mAudioResourceID;
    private int mImageResourceID = NO_IMAGE_PROVIDED;
    private final static int NO_IMAGE_PROVIDED = -1;

    public String name;



    public ImageAndSoundModel(int mImageResourceID, int mAudioResourceID, String name) {
        this.mAudioResourceID = mAudioResourceID;
        this.mImageResourceID = mImageResourceID;
        this.name = name;
    }

    public int getmAudioResourceID() {
        return mAudioResourceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getmImageResourceID() {
        return mImageResourceID;
    }

    public boolean hasImage(){
        return mImageResourceID != NO_IMAGE_PROVIDED;
    }


}
