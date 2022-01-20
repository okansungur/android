package com.example.guess3;

import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;


public class Game {

    private int arrayLength = 9;
    public ImageView[] buttons = new ImageView[arrayLength];
    public MainActivity mainClass;
    private int[] location = new int[arrayLength];
    public String selectedWordLevel[];
    ArrayList selectedButton = new ArrayList();

    public Game(ImageView[] inButtons, MainActivity mainActivity) {
        buttons = inButtons;
        mainClass = mainActivity;
        selectedWordLevel = mainActivity.gamelevel1; //init first level at the begining

        for (int i = 0; i < (arrayLength); i++) {
            location[i] = i;
            System.out.println("shuffle " + location[i]);
        }
    }


    public int checkAnswer(String x) {
        int tmp = -1;
        for (int i = 0; i < arrayLength; i++) {
            if (x.equals(i + "")) {
                tmp = i;
            }
        }
        return tmp;
    }

    public void getUnselectedButton(String letter) {
        for (int i = 0; i < arrayLength; i++) {
            if (!selectedButton.contains(i) && selectedButton.size() >= 0 && selectedButton.size() <= 9) {
                selectedButton.add(i);
                mainClass.ans[i].setText("" + letter);
                break;
            }
        }

    }

    public void showLevel() {
        for (int i = 0; i < selectedButton.size(); i++) {
            mainClass.playerMsg.setText(" Level :" + mainClass.mylevel);
        }
    }

    public void getCommand(View tile) {
        showLevel();
        if (checkAnswer(tile.getTag() + "") == -1) {

            tile.setVisibility(View.INVISIBLE);
            getUnselectedButton(tile.getTag() + "");

        } else {
            int selected = Integer.parseInt(tile.getTag() + "");
            if (selectedButton.contains(selected)) {
                for (int y = 0; y < selectedButton.size(); y++) {
                    if (selectedButton.get(y).equals(selected)) {
                        selectedButton.remove(y);
                    }
                }
            }

            String metin = mainClass.ans[selected].getText() + "";
            mainClass.ans[selected].setText("");
            getSelectedImageView(metin);
        }

        if (inspector()) {
            mainClass.showPopUpNext();
        }
    }

    void getSelectedImageView(String str) {

        for (int i = 0; i < arrayLength; i++) {

            if (buttons[i].getTag().equals(str)) {
                buttons[i].setVisibility(View.VISIBLE);
                break;
            }

        }


    }

    //Checks  whether the  answers for all the rows are correct or not
    public boolean inspector() {
        boolean result = false;
        boolean firstWord = false, secondWord = false, thirdWord = false;

        String firstRow = mainClass.ans[0].getText() + "" + mainClass.ans[1].getText() + "" + mainClass.ans[2].getText();
        String secondRow = mainClass.ans[3].getText() + "" + mainClass.ans[4].getText() + "" + mainClass.ans[5].getText();
        String thirdRow = mainClass.ans[6].getText() + "" + mainClass.ans[7].getText() + "" + mainClass.ans[8].getText();

        for (int i = 0; i < selectedWordLevel.length; i++) {
            if (selectedWordLevel[i].equals(firstRow)) {
                for (int i1 = 0; i1 < 3; i1++) {
                    mainClass.ans[i1].setBackgroundResource(R.drawable.button_shape2);
                    mainClass.ans[i1].setEnabled(false);
                    firstWord = true;
                }
            }
            if (selectedWordLevel[i].equals(secondRow)) {
                for (int i1 = 3; i1 < 6; i1++) {
                    mainClass.ans[i1].setEnabled(false);
                    mainClass.ans[i1].setBackgroundResource(R.drawable.button_shapetype);
                    secondWord = true;
                }
            }
            if (selectedWordLevel[i].equals(thirdRow)) {
                for (int i1 = 6; i1 < arrayLength; i1++) {
                    mainClass.ans[i1].setEnabled(false);
                    mainClass.ans[i1].setBackgroundResource(R.drawable.button_shape_yes);
                    thirdWord = true;
                }
            }
        }
        if (firstWord == true && secondWord == true && thirdWord == true) {
            result = true;
        }
        return result;
    }

    ArrayList liste = new ArrayList();
    ArrayList temporarylist = new ArrayList();

    public int getUniq() {
        int tmp;
        tmp = (int) (Math.random() * arrayLength);
        if (liste.contains(tmp)) {
            getUniq();
        } else {
            liste.add(tmp);
        }
        return tmp;
    }

    public void createListOfRandomTilePositions() {
        for (int i = 0; i < arrayLength; i++) {

            int j = getUniq();
            mainClass.ans[i].setVisibility(View.VISIBLE);
            mainClass.ans[i].setText("");
            mainClass.ans[i].setEnabled(true);
            mainClass.ans[i].setBackgroundResource(R.drawable.button_shape1);

            buttons[i].setVisibility(View.VISIBLE);
            buttons[i].setEnabled(true);

            int tempX = (int) buttons[location[j]].getX();
            int tempY = (int) buttons[location[j]].getY();

            buttons[location[j]].setX((int) buttons[location[i]].getX());
            buttons[location[j]].setY((int) buttons[location[i]].getY());
            buttons[location[i]].setX(tempX);
            buttons[location[i]].setY(tempY);

            for (int m = 0; m < arrayLength; m++) {
                buttons[m].setX((int) buttons[location[m]].getX());
                buttons[m].setY((int) buttons[location[m]].getY());
            }
        }

        temporarylist = liste;
        liste.clear();
        selectedButton.clear();

    }

}

