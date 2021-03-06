package com.ticcorp.retrofittest2.DTO;

import java.io.Serializable;

/**
 * Created by Daesub Kim on 2016-08-15.
 */
public class ScoreView  implements Serializable {

    private String name;
    private int exp;
    private int userLevel;

    public ScoreView() {
        // TODO Auto-generated constructor stub
    }

    public ScoreView(String name, int exp, int userLevel) {
        super();
        this.name = name;
        this.exp = exp;
        this.userLevel = userLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public String toString() {
        return "ScoreView [name=" + name + ", exp=" + exp + ", userLevel="
                + userLevel + "]";
    }


}
