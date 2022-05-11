package com.example.math_kidz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MultiplicationHard extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference referenceProfile;
    private FirebaseAuth mAuth;
    private int CorrectAnswer;
    TextView scoreTextView;
    int Score;
    Editable theInput;
    boolean positiveNumber = true;
    boolean hasDecimal = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_hard);


        mAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        //Extracts user reference from Database
        referenceProfile = firebaseDatabase.getInstance().getReference("Registered Users").child(mAuth.getUid());
        scoreTextView = (TextView) findViewById(R.id.ScoreTracker);

        getdata();

        ImageButton BackArrow = (ImageButton) findViewById(R.id.BackArrow);
        BackArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SubjectDifficultyMultiplication.class);
                startActivity(i);


            }
        });

//        try{
//            String s = scoreTextView.getText().toString();
//        }
//        catch(Exception e){
//          Toast.makeText(AdditionEasy.this, "Crash", Toast.LENGTH_SHORT).show();
//          }

        Random rand = new Random(); //instance of random class

        //generate random values from 0-99
        int upperbound = 1000;
        //generate random values from 0-9
        int upperbound2=10;
        int AddRand1 = rand.nextInt(upperbound);
        int AddRand2 = rand.nextInt(upperbound2);


        //generating answer for user to pick from
        CorrectAnswer = AddRand1 * AddRand2;



        TextView questionTextView = (TextView) findViewById(R.id.MultiplicationHardTextView);
        questionTextView.setText(AddRand1 + " * " + AddRand2);

        EditText UserInput = (EditText) findViewById(R.id.MultHardInput);

        Button AnswerChecker = (Button) findViewById(R.id.CheckButton);



        AnswerChecker.setOnClickListener(new View.OnClickListener() {



            public void onClick(View view) {

//Checking user input vs answer
                if (UserInput.getText().toString().equals(String.valueOf(CorrectAnswer))) {
                    boolean correct = true;
                    ScoreDisplay(Score, correct);
                    CorrectAnswer=QuestionGenerator();
                    UserInput.setText("");

                }
                else{
                    boolean incorrect = false;
                    ScoreDisplay(Score, incorrect);

                }

            }
            //Incrementing and decrementing score based on answer validity
            private void ScoreDisplay(int num, boolean ans) {
                if(ans == true)
                    Score = Score + 1;
                else
                    Score = Score - 1;
                referenceProfile.child("totalScore0").setValue(Score);
                getdata();


            }
            //method to generate new question
            public int QuestionGenerator() {
                int upperbound = 1000;
                int upperbound2=10;
                int AddRand1 = rand.nextInt(upperbound);
                int AddRand2 = rand.nextInt(upperbound2);
                int NewCorrectAnswer = AddRand1 * AddRand2;
                questionTextView.setText(AddRand1 + " * " + AddRand2);
                return  NewCorrectAnswer;


            }


        });





    }


    private void getdata() {

        // calling add value event listener method
        // for getting the values from database.
        referenceProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String score = snapshot.child("totalScore0").getValue().toString();
                scoreTextView.setText(score);
                Score = Integer.parseInt(score);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(MultiplicationHard.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });

    }
    //The following are the methods for the individual number buttons
    public void add0 (View v){
        TextView UserInput = findViewById(R.id.AddEasyInput);
        theInput = UserInput.getEditableText();
        UserInput.setText(theInput + "0");
    }
    public void add1 (View v){
        TextView UserInput = findViewById(R.id.AddEasyInput);
        theInput = UserInput.getEditableText();
        UserInput.setText(theInput + "1");
    }
    public void add2 (View v){
        TextView UserInput = findViewById(R.id.AddEasyInput);
        theInput = UserInput.getEditableText();
        UserInput.setText(theInput + "2");
    }
    public void add3 (View v){
        TextView UserInput = findViewById(R.id.AddEasyInput);
        theInput = UserInput.getEditableText();
        UserInput.setText(theInput + "3");
    }
    public void add4 (View v){
        TextView UserInput = findViewById(R.id.AddEasyInput);
        theInput = UserInput.getEditableText();
        UserInput.setText(theInput + "4");
    }
    public void add5 (View v){
        TextView UserInput = findViewById(R.id.AddEasyInput);
        theInput = UserInput.getEditableText();
        UserInput.setText(theInput + "5");
    }
    public void add6 (View v){
        TextView UserInput = findViewById(R.id.AddEasyInput);
        theInput = UserInput.getEditableText();
        UserInput.setText(theInput + "6");
    }
    public void add7 (View v){
        TextView UserInput = findViewById(R.id.AddEasyInput);
        theInput = UserInput.getEditableText();
        UserInput.setText(theInput + "7");
    }
    public void add8 (View v){
        TextView UserInput = findViewById(R.id.AddEasyInput);
        theInput = UserInput.getEditableText();
        UserInput.setText(theInput + "8");
    }
    public void add9 (View v){
        TextView UserInput = findViewById(R.id.AddEasyInput);
        theInput = UserInput.getEditableText();
        UserInput.setText(theInput + "9");
    }
    public void addDecimal (View v){
        TextView UserInput = findViewById(R.id.AddEasyInput);
        theInput = UserInput.getEditableText();
        if (hasDecimal == false){
            UserInput.setText(theInput + ".");
            hasDecimal = true;
        }
    }
    public void clearCalculator (View v){
        TextView UserInput = findViewById(R.id.AddEasyInput);
        theInput = UserInput.getEditableText();
        UserInput.setText("");
        hasDecimal = false;
    }
    public void addNegative (View v){
        TextView UserInput = findViewById(R.id.AddEasyInput);
        theInput = UserInput.getEditableText();

        if (positiveNumber == true){
            UserInput.setText("-" + theInput);
            positiveNumber = false;
        }
        else{
            theInput.delete(0,1);
            UserInput.setText(theInput);
            positiveNumber = true;
        }
    }

}