package com.example.guess3;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {


    private PopupWindow mPopupNext;

    PopupWindow myPopupHelp;
    Button button_help;

    ImageView[] buttons = new ImageView[9];
    ImageView a0, a1, a2, a3, a4, a5, a6, a7, a8;
    public Button[] ans = new Button[9];
    public int mylevel = 1;
    private String msg;

    public TextView playerMsg;


    //region WordList
    public String gamelevel1[] = {"ace", "hog", "its"};
    public String gamelevel2[] = {"ion", "jam", "que"};
    public String gamelevel3[] = {"cut", "men", "wry"};
    public String gamelevel4[] = {"tax", "wig", "hub"};
    public String gamelevel5[] = {"sag", "kit", "mud"};
    public String gamelevel6[] = {"tan", "gel", "cop"};
    public String gamelevel7[] = {"ale", "bio", "put"};
    public String gamelevel8[] = {"jog", "kin", "ape"};
    public String gamelevel9[] = {"act", "fin", "rep"};
    public String gamelevel10[] = {"pie", "hug", "fry"};
    public String gamelevel11[] = {"sea", "win", "fog"};
    public String gamelevel12[] = {"cab", "end", "lip"};
    public String gamelevel13[] = {"mug", "row", "via"};
    public String gamelevel14[] = {"ply", "jaw", "dog"};
    public String gamelevel15[] = {"cob", "awe", "try"};
    public String gamelevel16[] = {"mob", "sky", "hue"};
    public String gamelevel17[] = {"tug", "sew", "fly"};
    public String gamelevel18[] = {"log", "aim", "cry"};
    public String gamelevel19[] = {"pea", "mix", "fur"};
    public String gamelevel20[] = {"bye", "apt", "ski"};

    //endregion


    public static final String mypref_name1 = "GuessWord3";

    Preferences pref = new Preferences();

    RelativeLayout layoutRenk;
    public int tempWord[] = new int[9];
    public int words[] = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o, R.drawable.p, R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t, R.drawable.u, R.drawable.w, R.drawable.v, R.drawable.y, R.drawable.z, R.drawable.x};
    public Game game = new Game(buttons, this);
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout lay = (RelativeLayout) findViewById(R.id.rel1);
        layoutRenk = (RelativeLayout) findViewById(R.id.relColor);

        ctx = getApplicationContext();
        playerMsg = (TextView) findViewById(R.id.playerMsg);

        if (hepsi.size() > 0) hepsi.clear();


        button_help = (Button) findViewById(R.id.button_help);


        button_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUpHelp();
                game.createListOfRandomTilePositions();
                getRandomColor();

            }
        });

        addtoList();
        initGame();

    }


    public void initGame() {
        tempWordList.clear();
        levelChooser();
        fillAnswerButtons();
        selectLevel();
    }

    String[] diziColor = {"#CDEBC6", "#ecdbf5", "#fdf49b", "#d5f2e6", "#ffffff"};

    public void getRandomColor() {
        Random rnd = new Random();
        int secili = rnd.nextInt(4);
        layoutRenk.setBackgroundColor(Color.parseColor(diziColor[secili]));
    }


    public ArrayList<Integer> tempWordList = new ArrayList<Integer>();

    public void initTempWords(String dizi[]) {

        for (int i = 0; i < dizi.length; i++) {

            for (int j = 0; j < dizi[i].length(); j++) {
                String nameChar = dizi[i].charAt(j) + "";

                int gID = getIDFromName(nameChar);

                tempWordList.add(gID);

            }

        }
        fillTempWord();
    }

    public void fillTempWord() {
        Collections.shuffle(tempWordList);
        for (int j = 0; j < 9; j++) {
            tempWord[j] = tempWordList.get(j);

        }
    }

    public int getIDFromName(String str) {
        int temp = -1;
        for (int i = 0; i < words.length; i++) {

            String name = ctx.getResources().getResourceEntryName(words[i]) + "";
            if (name.equals(str)) {
                temp = words[i];

                break;
            }
        }

        return temp;
    }


    public void fillAnswerButtons() {

        ans[0] = (Button) findViewById(R.id.mbutton0);
        ans[1] = (Button) findViewById(R.id.mbutton1);
        ans[2] = (Button) findViewById(R.id.mbutton2);
        ans[3] = (Button) findViewById(R.id.mbutton3);
        ans[4] = (Button) findViewById(R.id.mbutton4);
        ans[5] = (Button) findViewById(R.id.mbutton5);
        ans[6] = (Button) findViewById(R.id.mbutton6);
        ans[7] = (Button) findViewById(R.id.mbutton7);
        ans[8] = (Button) findViewById(R.id.mbutton8);


    }

    //Get which button is clicked
    public void clickButton(View tile) {

        game.getCommand(tile);
    }


    public String getResourceName(int id) {
        String tmp = "";
        String name = ctx.getResources().getResourceEntryName(id);
        tmp = name;
        return tmp;
    }

    public void selectLevel() {
        a0 = (ImageView) findViewById(R.id.button0);
        a0.setTag(getResourceName(tempWord[0]) + "");
        a0.setImageResource(tempWord[0]);
        a1 = (ImageView) findViewById(R.id.button1);
        a1.setTag(getResourceName(tempWord[1]) + "");
        a1.setImageResource(tempWord[1]);
        a2 = (ImageView) findViewById(R.id.button2);
        a2.setTag(getResourceName(tempWord[2]) + "");
        a2.setImageResource(tempWord[2]);
        a3 = (ImageView) findViewById(R.id.button3);
        a3.setImageResource(tempWord[3]);
        a3.setTag(getResourceName(tempWord[3]) + "");
        a4 = (ImageView) findViewById(R.id.button4);
        a4.setImageResource(tempWord[4]);
        a4.setTag(getResourceName(tempWord[4]) + "");
        a5 = (ImageView) findViewById(R.id.button5);
        a5.setImageResource(tempWord[5]);
        a5.setTag(getResourceName(tempWord[5]) + "");
        a6 = (ImageView) findViewById(R.id.button6);
        a6.setImageResource(tempWord[6]);
        a6.setTag(getResourceName(tempWord[6]) + "");

        a7 = (ImageView) findViewById(R.id.button7);
        a7.setImageResource(tempWord[7]);
        a7.setTag(getResourceName(tempWord[7]) + "");
        a8 = (ImageView) findViewById(R.id.button8);
        a8.setImageResource(tempWord[8]);
        a8.setTag(getResourceName(tempWord[8]) + "");

        game.buttons[0] = a0;
        game.buttons[1] = a1;
        game.buttons[2] = a2;
        game.buttons[3] = a3;
        game.buttons[4] = a4;
        game.buttons[5] = a5;
        game.buttons[6] = a6;
        game.buttons[7] = a7;
        game.buttons[8] = a8;


    }

    public Drawable getDrawable(String name1) {

        int resourceId = ctx.getResources().getIdentifier(name1, "drawable", ctx.getPackageName());
        return ctx.getResources().getDrawable(resourceId);
    }

    public void showPopUpHelp() {
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.popuphelp, null);
        ImageButton closeHelp = (ImageButton) customView.findViewById(R.id.hlp_close);
        myPopupHelp = new PopupWindow(
                customView,
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );


        closeHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myPopupHelp.dismiss();
            }
        });


        myPopupHelp.setTouchable(true);
        myPopupHelp.setFocusable(true);
        myPopupHelp.showAtLocation(button_help, Gravity.CENTER, 0, 0);


    }

    public void showPopUpNext() {

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.popupnext, null);
        mPopupNext = new PopupWindow(
                customView,
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );


        Button nextLevel = (Button) customView.findViewById(R.id.nextLevel);

        nextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mylevel += 1;
                pref.writeSharedPreference(getApplicationContext(), mypref_name1, "lastlevel", mylevel + "");
                initGame();
                game.createListOfRandomTilePositions();
                mPopupNext.dismiss();
                playerMsg.setText(" Level :" + mylevel);


            }
        });

        mPopupNext.setTouchable(true);
        mPopupNext.setFocusable(true);


    }


    public String[] getRandomElement() {
        String[] tmp = null;
        Random r = new Random();
        int x = r.nextInt(80) + 1;
        tmp = hepsi.get(x);

        return tmp;
    }

    //region LevelChooser

    public void levelChooser() {

        String getLevel = pref.readSharedPreference(this, mypref_name1, "lastlevel");
        if (getLevel == null || getLevel.equals("")) {
            mylevel = 1;
        } else {

            int seviye = Integer.parseInt(getLevel + "");
            mylevel = seviye;
        }


        switch (mylevel) {
            case 1:
                initTempWords(gamelevel1);
                game.selectedWordLevel = gamelevel1;
                break;
            case 2:
                initTempWords(gamelevel2);
                game.selectedWordLevel = gamelevel2;
                break;
            case 3:
                initTempWords(gamelevel3);
                game.selectedWordLevel = gamelevel3;
                break;
            case 4:
                initTempWords(gamelevel4);
                game.selectedWordLevel = gamelevel4;
                break;
            case 5:
                initTempWords(gamelevel5);
                game.selectedWordLevel = gamelevel5;
                break;
            case 6:
                initTempWords(gamelevel6);
                game.selectedWordLevel = gamelevel6;
                break;
            case 7:
                initTempWords(gamelevel7);
                game.selectedWordLevel = gamelevel7;
                break;
            case 8:
                initTempWords(gamelevel8);
                game.selectedWordLevel = gamelevel8;
                break;
            case 9:
                initTempWords(gamelevel9);
                game.selectedWordLevel = gamelevel9;
                break;
            case 10:
                initTempWords(gamelevel10);
                game.selectedWordLevel = gamelevel10;
                break;
            case 11:
                initTempWords(gamelevel11);
                game.selectedWordLevel = gamelevel11;
                break;
            case 12:
                initTempWords(gamelevel12);
                game.selectedWordLevel = gamelevel12;
                break;
            case 13:
                initTempWords(gamelevel13);
                game.selectedWordLevel = gamelevel13;
                break;
            case 14:
                initTempWords(gamelevel14);
                game.selectedWordLevel = gamelevel14;
                break;
            case 15:
                initTempWords(gamelevel15);
                game.selectedWordLevel = gamelevel15;
                break;
            case 16:
                initTempWords(gamelevel16);
                game.selectedWordLevel = gamelevel16;
                break;
            case 17:
                initTempWords(gamelevel17);
                game.selectedWordLevel = gamelevel17;
                break;
            case 18:
                initTempWords(gamelevel18);
                game.selectedWordLevel = gamelevel18;
                break;
            case 19:
                initTempWords(gamelevel19);
                game.selectedWordLevel = gamelevel19;
                break;
            case 20:
                initTempWords(gamelevel20);
                game.selectedWordLevel = gamelevel20;
                break;


            default:

                String[] rndDizi = getRandomElement();
                initTempWords(rndDizi);
                game.selectedWordLevel = rndDizi;
                break;
        }

    }

////endregion


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;

    }


    //region  LevelList

    List<String[]> hepsi = new ArrayList<String[]>();

    public void addtoList() {
        hepsi.add(gamelevel1);
        hepsi.add(gamelevel2);
        hepsi.add(gamelevel3);
        hepsi.add(gamelevel4);
        hepsi.add(gamelevel5);
        hepsi.add(gamelevel6);
        hepsi.add(gamelevel7);
        hepsi.add(gamelevel8);
        hepsi.add(gamelevel9);
        hepsi.add(gamelevel10);
        hepsi.add(gamelevel11);
        hepsi.add(gamelevel12);
        hepsi.add(gamelevel13);
        hepsi.add(gamelevel14);
        hepsi.add(gamelevel15);

        hepsi.add(gamelevel16);
        hepsi.add(gamelevel17);
        hepsi.add(gamelevel18);
        hepsi.add(gamelevel19);
        hepsi.add(gamelevel20);


    }
//endregion


}







