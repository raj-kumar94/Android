package com.example.raj.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2}; //2=unplayed
    //to know which grid has been tapped, use tag in xml
    boolean gameIsActive  = true;

    public void dropIn(View view)
    {
        ImageView counter = (ImageView)view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        int[][] winnigPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        if(gameState[tappedCounter]==2 && gameIsActive) {
            gameState[tappedCounter]=activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.blue);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);

            for(int[] winnigPostition: winnigPositions)
            {
                if(gameState[winnigPostition[0]]==gameState[winnigPostition[1]]&&
                        gameState[winnigPostition[1]]== gameState[winnigPostition[2]]&&
                        gameState[winnigPostition[0]]!=2)
                {
                    System.out.println(gameState[winnigPostition[0]]);

                    //if someone has won
                    gameIsActive = false;
                    String winner = "cross";
                    if(gameState[winnigPostition[0]]==0)
                    {
                        winner = "circle";
                    }
                    TextView tView = (TextView)findViewById(R.id.textView);
                    tView.setText(winner+" has won!");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                }
                else //else the game is a draw
                {
                    boolean gameIsOver = true;
                    for(int counterState: gameState)
                    {
                        if(counterState==2)
                            gameIsOver=false;
                    }

                    if(gameIsOver)
                    {
                        TextView tView = (TextView)findViewById(R.id.textView);
                        tView.setText("Its a draw!");

                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }

            }

        }
    }

    public void playAgain(View view)
    {
        gameIsActive = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;

        for(int i=0; i<gameState.length;i++)
        {
            gameState[i]=2;
        }

        GridLayout gl = (GridLayout)findViewById(R.id.gridLayout);
        for(int j=0; j<gl.getChildCount();j++)
        {
            ((ImageView)gl.getChildAt(j)).setImageResource(0); //setting empty image
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
