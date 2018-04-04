package com.example.asifhashmi.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int player1Score=0;
    int player2Score=0;
    int chipSelect=0;
    boolean isGameActive=true;

    int allChipsState[]={2,2,2,2,2,2,2,2,2};

    public void dropDown(View view){
        ImageView chip=(ImageView) view;
        int blockSelected=Integer.parseInt(chip.getTag().toString());
        int winningSequence[][]={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};

        if(allChipsState[blockSelected-1]==2 && isGameActive ) {
            // yellow=1 and red=0
            chip.setTranslationY(-1000f);

            if (chipSelect == 0) {
                allChipsState[blockSelected-1]=1;
                chip.setImageResource(R.drawable.yellow);
                chipSelect = 1;
            } else {
                allChipsState[blockSelected-1]=0;
                chip.setImageResource(R.drawable.red);
                chipSelect = 0;
            }

            chip.animate().translationYBy(1000f).rotation(100f).setDuration(200);

            for(int seq[] :winningSequence){
                if(allChipsState[seq[0]]==allChipsState[seq[1]]&&allChipsState[seq[1]]==allChipsState[seq[2]]&&allChipsState[seq[0]]!=2){Log.i("nosssss","win win");
                    isGameActive=false;
                    wonOnwards(allChipsState[seq[0]],view);

                }
            }

            int drawCount=0;

            for(int i=0;i<allChipsState.length;i++){
                if(allChipsState[i]!=2){
                    drawCount+=1;
                }
                if(drawCount==9){
                    drawFunction();
                }
            }

        }
    }

    public void drawFunction(){
        String whoWon="Draw";

        TextView whoWonOutput=(TextView) findViewById(R.id.textView);

        whoWonOutput.setText(whoWon);

        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linearL);
        if(linearLayout.getVisibility()!=View.VISIBLE) {
            linearLayout.setVisibility(View.VISIBLE);
        }
    }

    public void playGameMain(View view){

        EditText editTextPlayer1Name=(EditText)findViewById(R.id.player1Name);
        EditText editTextPlayer2Name=(EditText)findViewById(R.id.player2Name);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        TextView textViewPlayer1Name = (TextView) findViewById(R.id.player1NameSeen);
        TextView textViewPlayer2Name = (TextView) findViewById(R.id.player2NameSeen);
        Button playG = (Button) findViewById(R.id.playG);


        if(editTextPlayer1Name.getText().toString().matches("")){
            Toast.makeText(MainActivity.this," Enter name of player 1 ",Toast.LENGTH_SHORT).show();
        }else if( editTextPlayer2Name.getText().toString().matches("")){
            Toast.makeText(MainActivity.this," Enter name of player 2 ",Toast.LENGTH_SHORT).show();
        }else {


            TextView title = (TextView) findViewById(R.id.title);

            textViewPlayer1Name.setText(editTextPlayer1Name.getText().toString());
            textViewPlayer2Name.setText(editTextPlayer2Name.getText().toString());

            //now hiding top layer

            editTextPlayer1Name.setVisibility(View.INVISIBLE);
            editTextPlayer2Name.setVisibility(View.INVISIBLE);

            if (title != null)
                title.setVisibility(View.INVISIBLE);

            playG.setVisibility(View.INVISIBLE);
            frameLayout.setVisibility(View.INVISIBLE);
        }
    }

    public void wonOnwards(int yellowOrRed,View view){
        String yr="Red";
        if(yellowOrRed==1){
            yr="Yellow";
            player1Score+=1;
            TextView player1ScoreOutput=(TextView) findViewById(R.id.player1Score);
            player1ScoreOutput.setText(Integer.toString(player1Score));
        }else{
            player2Score+=1;
            TextView player1ScoreOutput=(TextView) findViewById(R.id.player2Score);
            player1ScoreOutput.setText(Integer.toString(player2Score));
        }



        String whoWon=yr+" won the game";

        TextView whoWonOutput=(TextView) findViewById(R.id.textView);

        whoWonOutput.setText(whoWon);

        //visibility
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linearL);
        if(linearLayout.getVisibility()!=View.VISIBLE) {
            linearLayout.setVisibility(View.VISIBLE);
        }

    }

    public void newGame(View view){
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linearL);
        isGameActive=true;
        chipSelect=0;
        if(linearLayout.getVisibility()!=View.INVISIBLE) {
            linearLayout.setVisibility(View.INVISIBLE);
        }

        for(int i=0;i<allChipsState.length;i++){
            allChipsState[i]=2;
        }

        GridLayout gridLayout=(GridLayout)findViewById(R.id.table);

        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
