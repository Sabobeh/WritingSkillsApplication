package com.example.writingskillsapplication;


import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class DatabaseItems {

    private List<Resource> resources = new ArrayList<Resource>();

    public DatabaseItems(){
        resources.add(new Resource("https://www.scribbr.com/category/academic-essay/#:~:text=Preparation%3A%20Decide%20on%20your%20topic,and%20formatting%20of%20your%20essay.", "Beginner"));
        resources.add(new Resource("https://www.uopeople.edu/blog/how-to-write-an-essay/", "Beginner"));
        resources.add(new Resource("https://www.slideshare.net/crazylily13/workshop-36760665", "Intermediate "));
        resources.add(new Resource("https://engxam.com/handbook/how-to-write-an-essay-c1-advanced-cae/", "Advanced"));
        resources.add(new Resource("https://oxfordhousebcn.com/en/how-to-write-a-c1-advanced-essay/", "Advanced"));

    }

    public List<Resource> getMenuItems(String level){
        List<Resource> result = new ArrayList<>();
        for(Resource m: resources){
            if(m.getLevel().equals(level)){
                result.add(m);
            }
        }
        return result;

    }

    public String[] getLevels(){
        //assume we are reading data from database
        return new String[]{"Advanced", "Intermediate ", "Beginner"};

    }


}