package net.grasinga.smiterandomizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String build = "";
    God godClass;
    Shop shopClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        godClass = new God(getResources());
        shopClass = new Shop(getResources());

        if(!build.equalsIgnoreCase(""))
            runRandom();

        TextView textView =(TextView)findViewById(R.id.patchNotes);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://grasinga.net/files/others/SMITE/history.html'>Patch Notes</a>";
        textView.setText(Html.fromHtml(text));

        Button randomButton = (Button)findViewById(R.id.randomButton);
        Button customizeButton = (Button)findViewById(R.id.customizeButton);

        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runRandom();
            }
        });
        customizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Customize.class));
                finish();
            }
        });
    }

    public void runRandom(){
        String info = "";
        int r = (int) (Math.random()*(godClass.gods.size()));
        String god = godClass.gods.get(r);
        info += ("God: " + god + "\n\n");
        info += getRandomBuild(god);
        if(build.equalsIgnoreCase(""))
            build = info;
        TextView t = (TextView) findViewById(R.id.textView);
        t.setText(build);
        build = "";
    }

    public String getGodType(String god){
        if(godClass.physicalGods.contains(god))
            return "Physical";
        else if(godClass.magicalGods.contains(god))
            return "Magical";
        return "No god type found!";
    }

    public String getGodClass(String god){
        if(godClass.assassins.contains(god))
            return "Assassin";
        else if(godClass.warriors.contains(god))
            return "Warrior";
        else if(godClass.guardians.contains(god))
            return "Guardian";
        else if(godClass.mages.contains(god))
            return "Mage";
        else if(godClass.hunters.contains(god))
            return "Hunter";
        return "No god class found!";
    }

    public String getRandomBuild(String god){
        String buildText = "";
        ArrayList<String> build = new ArrayList<>();
        ArrayList<String> relics = new ArrayList<>();
        String item;
        if(getGodType(god).equalsIgnoreCase("Physical")){
            // Get the Starter Item.
            int check = 0;
            while(check < 1){
                int r = (int)(Math.random()*(shopClass.starterItems.size()));
                item = shopClass.starterItems.get(r);
                if(!shopClass.magical.contains(item)){
                    buildText += "Starter Item: " + item + "\n\n";
                    check++;
                }
            }
            // Get the Relics.
            check = 0;
            buildText += "Relics: ";
            while(check < 2){
                int r = (int)(Math.random()*(shopClass.relics.size()));
                item = shopClass.relics.get(r);
                if(!shopClass.magical.contains(item) && !relics.contains(item)){
                    relics.add(item);
                    check++;
                }
            }
            for(int i=0;i<relics.size();i++)
                buildText += relics.get(i) + ", ";
            buildText = buildText.substring(0,buildText.length()-2) + "\n\n";
            // Get the main build.
            int ran;
            if(godClass.isUnique.contains(god)){
                ran = (int) (Math.random()*(shopClass.uniqueItems.size()));
                item = shopClass.uniqueItems.get(ran);
                build.add(item);
            }
            int numOfBoots = 0;
            while(build.size() < 6){
                ran = (int)(Math.random()*(shopClass.allItems.size()));
                item = shopClass.allItems.get(ran);
                if(shopClass.boots.contains(item))
                    numOfBoots++;
                if(!shopClass.consumable.contains(item) && !shopClass.relics.contains(item) && !shopClass.starterItems.contains(item) && !shopClass.magical.contains(item) && !build.contains(item))
                    if((shopClass.meleeOnly.contains(item) && !getGodClass(god).equalsIgnoreCase("Hunter")) || !shopClass.meleeOnly.contains(item))
                        if(!shopClass.selfHeal.contains(item) || (shopClass.selfHeal.contains(item) && godClass.hasHeal.contains(god)))
                            if(numOfBoots <= 1)
                                build.add(item);
                            else
                                numOfBoots = 1;
            }
        }
        else if(getGodType(god).equalsIgnoreCase("Magical")){
            // Get the Starter Item.
            int check = 0;
            while(check < 1){
                int r = (int)(Math.random()*(shopClass.starterItems.size()));
                item = shopClass.starterItems.get(r);
                if(!shopClass.physical.contains(item)){
                    buildText += "Starter Item: " + item + "\n\n";
                    check++;
                }
            }
            // Get the Relics.
            check = 0;
            buildText += "Relics: ";
            while(check < 2){
                int r = (int)(Math.random()*(shopClass.relics.size()));
                item = shopClass.relics.get(r);
                if(!shopClass.physical.contains(item) && !relics.contains(item)){
                    relics.add(item);
                    check++;
                }
            }
            for(int i=0;i<relics.size();i++)
                buildText += relics.get(i) + ", ";
            buildText = buildText.substring(0,buildText.length()-2) + "\n\n";
            // Get the main build.
            int numOfBoots = 0;
            while(build.size() < 6){
                int r = (int)(Math.random()*(shopClass.allItems.size()));
                item = shopClass.allItems.get(r);
                if(shopClass.boots.contains(item))
                    numOfBoots++;
                if(!shopClass.consumable.contains(item) && !shopClass.relics.contains(item) && !shopClass.starterItems.contains(item) && !shopClass.physical.contains(item) && !build.contains(item))
                    if(!shopClass.selfHeal.contains(item) || (shopClass.selfHeal.contains(item) && godClass.hasHeal.contains(god)))
                        if(numOfBoots <= 1)
                            build.add(item);
                        else
                            numOfBoots = 1;
            }
        }
        if(build.size() > 0){
            buildText += "Build:\n";
            for(int i=0;i<build.size();i++)
                buildText += build.get(i) + "\n";
            buildText += "\nPress the 'Random' or 'Customize' button to randomize again!";
            return buildText;
        }
        return "No build found due to unknown god type or class!";
    }
}
