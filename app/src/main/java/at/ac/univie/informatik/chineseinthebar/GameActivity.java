package at.ac.univie.informatik.chineseinthebar;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameActivity extends AppCompatActivity {

    private Translation word;
    private Random randomGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        randomGenerator = new Random();

        generateWord();

        TextView english;
        english = (TextView)findViewById(R.id.english);
        english.setText(word.getEnglish());

        TextView chinese;
        english = (TextView)findViewById(R.id.chinese);
        english.setText(word.getChinese());

        TextView pinyin;
        english = (TextView)findViewById(R.id.pinyin);
        english.setText(word.getPinyin());

        AlertDialog.Builder instBuilder = new AlertDialog.Builder(GameActivity.this);
        instBuilder.setMessage("Try pronouncing the phrase on the screen.\n" +
                "You have three tries.\n" +
                "If you fail three times, you have to drink.");
        instBuilder.setCancelable(false);
        instBuilder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                firstTry();
            }
        });


        AlertDialog instructions = instBuilder.create();
        instructions.setTitle("Instructions");
        instructions.show();


        /*
        FloatingActionButton fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        FloatingActionButton fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        FloatingActionButton fab3 = (FloatingActionButton)findViewById(R.id.fab3);
        */


    }

    public void firstTry() {
        AlertDialog.Builder successBuilder = new AlertDialog.Builder(GameActivity.this);
        successBuilder.setMessage("Congratulations!\nWould you like to get a new word?");
        successBuilder.setCancelable(false);
        successBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GameActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
        successBuilder.setNegativeButton("No thanks", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog success = successBuilder.create();
        success.setTitle("Success!");

        AlertDialog.Builder failBuilder = new AlertDialog.Builder(GameActivity.this);
        failBuilder.setMessage("2 tries left");
        failBuilder.setCancelable(false);
        failBuilder.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                secondTry();
            }
        });
        AlertDialog fail = failBuilder.create();
        fail.setTitle("Failure!");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean correct = correct();
        if (correct) {
            success.show();
        }
        if (!correct) {
            fail.show();
        }

    }

    public void secondTry() {
        AlertDialog.Builder successBuilder = new AlertDialog.Builder(GameActivity.this);
        successBuilder.setMessage("Congratulations!\nWould you like to get a new word?");
        successBuilder.setCancelable(false);
        successBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GameActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
        successBuilder.setNegativeButton("No thanks", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog success = successBuilder.create();
        success.setTitle("Success!");

        AlertDialog.Builder failBuilder = new AlertDialog.Builder(GameActivity.this);
        failBuilder.setMessage("1 try left");
        failBuilder.setCancelable(false);
        failBuilder.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                thirdTry();
            }
        });
        AlertDialog fail = failBuilder.create();
        fail.setTitle("Failure!");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean correct = correct();
        if (correct) {
            success.show();
        }
        if (!correct) {
            fail.show();
        }

    }

    public void thirdTry() {
        AlertDialog.Builder successBuilder = new AlertDialog.Builder(GameActivity.this);
        successBuilder.setMessage("Congratulations!\nWould you like to get a new word?");
        successBuilder.setCancelable(false);
        successBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GameActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
        successBuilder.setNegativeButton("No thanks", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog success = successBuilder.create();
        success.setTitle("Success!");

        AlertDialog.Builder failBuilder = new AlertDialog.Builder(GameActivity.this);
        failBuilder.setMessage("You have to drink.");
        failBuilder.setCancelable(false);
        failBuilder.setPositiveButton("Get a new word", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GameActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
        failBuilder.setNegativeButton("Quit game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog fail = failBuilder.create();
        fail.setTitle("Failure!");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean correct = correct();
        if (correct) {
            success.show();
        }
        if (!correct) {
            fail.show();
        }

    }

    public boolean correct() {
        boolean correct = false;
        double random = Math.random();
        if (random < 0.191) {
            correct = true;
        }
        return correct;
    }

    private void generateWord() {
        String category;
        double random = Math.random();
        if (random < 0.5)
            category = "eat";
        else
            category = "drink";
        List<Translation> translations = Settings.getTranslation(this, "WordsJSON", category);

        int index = randomGenerator.nextInt(translations.size());
        word = translations.get(index);

    }

    // creates instruction dialog on startup
    /* public void checkFirstRun() {
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if (isFirstRun){ */


            // set to always true for testing purposes
            /* getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", true)
                    .apply();
        }
    }*/

}
