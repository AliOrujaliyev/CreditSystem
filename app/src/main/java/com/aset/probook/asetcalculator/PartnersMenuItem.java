package com.aset.probook.asetcalculator;

/**
 * Created by Probook on 20.04.2018.
 */

public class PartnersMenuItem {

    private int ID;
    private int MenuName;
    private int MenuImage;

    public PartnersMenuItem(int id, int menuName) {
        ID = id;
        MenuName = menuName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMenuName() {
        return MenuName;
    }

    public void setMenuName(int menuName) {
        MenuName = menuName;
    }


}

