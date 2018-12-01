package com.example.word;

/**
 * Created by Meo on 2018/11/29.
 */

public class Word {
    private String name;
    private String explanation;
    private String example;
    public Word(String name,String explanation,String example){
        this.name=name;
        this.explanation=explanation;
        this.example=example;
    }
    public Word(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }
    public String getExplanation() {
        return explanation;
    }
    public String getExample() {
        return example;
    }
}
