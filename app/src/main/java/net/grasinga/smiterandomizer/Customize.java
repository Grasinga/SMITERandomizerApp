package net.grasinga.smiterandomizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Customize extends AppCompatActivity {

    God godClass;
    Shop shopClass;

    String buildText = "";
    ArrayList<String> localGodList;
    String[] listOfGods;
    String[] listOfGodTypes = {"Not Specified", "Physical", "Magical"};
    String[] listOfGodClasses = {"Not Specified", "Assassin","Guardian","Hunter","Mage","Warrior"};

    // Drop down lists:
    Spinner godList;
    Spinner godTypeList;
    Spinner godClassList;

    // Column 1:
    CheckBox offensive;
    CheckBox power;
    CheckBox attackSpeed;
    CheckBox lifeSteal;
    CheckBox penetration;
    CheckBox critChance;

    // Column 2:
    CheckBox defensive;
    CheckBox physical;
    CheckBox magical;
    CheckBox health;
    CheckBox hp5;

    // Column 3:
    CheckBox utility;
    CheckBox aura;
    CheckBox movement;
    CheckBox cooldown;
    CheckBox mana;
    CheckBox mp5;

    // Bottom check boxes:
    CheckBox starterItemBox;
    CheckBox relicsBox;
    CheckBox alwaysBoots;

    Button submit;

    String godName = "";
    String godBuild = "";
    boolean getStarterItem = false;
    boolean getRelics = false;
    boolean getBootsAlways = false;

    // Column 1:
    boolean offensiveChecked = false;
    boolean powerChecked = false;
    boolean attackSpeedChecked = false;
    boolean lifeStealChecked = false;
    boolean penetrationChecked = false;
    boolean critChanceChecked = false;

    // Column 2:
    boolean defensiveChecked = false;
    boolean physicalChecked = false;
    boolean magicalChecked = false;
    boolean healthChecked = false;
    boolean hp5Checked = false;

    // Column 3:
    boolean utilityChecked = false;
    boolean auraChecked = false;
    boolean movementChecked = false;
    boolean cooldownChecked = false;
    boolean manaChecked = false;
    boolean mp5Checked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        godClass = new God(getResources());
        shopClass = new Shop(getResources());

        localGodList = godClass.gods;
        localGodList.add(0,"Not Specified");
        listOfGods = localGodList.toArray(new String[localGodList.size()]);

        // Drop down lists:
        godList = (Spinner)findViewById(R.id.godSpinner);
        godTypeList = (Spinner)findViewById(R.id.godTypeSpinner);
        godClassList = (Spinner)findViewById(R.id.godClassSpinner);

        godList.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listOfGods));
        godTypeList.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listOfGodTypes));
        godClassList.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listOfGodClasses));

        // Column 1:
        offensive = (CheckBox)findViewById(R.id.offensiveCheckBox);
        power = (CheckBox)findViewById(R.id.powerCheckBox);
        attackSpeed = (CheckBox)findViewById(R.id.attackSpeedCheckBox);
        lifeSteal = (CheckBox)findViewById(R.id.lifeStealCheckBox);
        penetration = (CheckBox)findViewById(R.id.penetrationCheckBox);
        critChance = (CheckBox)findViewById(R.id.critChanceCheckBox);

        // Column 2:
        defensive = (CheckBox)findViewById(R.id.defensiveCheckBox);
        physical = (CheckBox)findViewById(R.id.physicalCheckBox);
        magical = (CheckBox)findViewById(R.id.magicalCheckBox);
        health = (CheckBox)findViewById(R.id.healthCheckBox);
        hp5 = (CheckBox)findViewById(R.id.hp5CheckBox);

        // Column 3:
        utility = (CheckBox)findViewById(R.id.utilityCheckBox);
        aura = (CheckBox)findViewById(R.id.auraCheckBox);
        movement = (CheckBox)findViewById(R.id.movementCheckBox);
        cooldown = (CheckBox)findViewById(R.id.cooldownCheckBox);
        mana = (CheckBox)findViewById(R.id.manaCheckBox);
        mp5 = (CheckBox)findViewById(R.id.mp5CheckBox);

        // Bottom check boxes:
        starterItemBox = (CheckBox)findViewById(R.id.starterItemCheckBox);
        relicsBox = (CheckBox)findViewById(R.id.randomRelicsCheckBox);
        alwaysBoots = (CheckBox)findViewById(R.id.bootsCheckBox);

        submit = (Button)findViewById(R.id.submitButton);

        setListeners();
    }

    public void getGodBuild(View view){
        // Build Variables:
        godName = getGod();
        godBuild = getSpecificBuild(godName);
        godName = "God: " + godName + "\n\n";
        startActivity(new Intent(Customize.this, MainActivity.class));
        MainActivity.build = (godName + godBuild);
        finish();
    }

    public String getSpecificBuild(String god){
        ArrayList<String> customBuild = new ArrayList<>();
        if(offensiveChecked && defensiveChecked && utilityChecked)
            return getBuild(god, shopClass.allItems);
        else{
            if(offensiveChecked)
                for(int i=0;i<shopClass.offensive.size();i++)
                    customBuild.add(shopClass.offensive.get(i));
            else{
                if(powerChecked)
                    for(int i=0;i<shopClass.power.size();i++)
                        customBuild.add(shopClass.power.get(i));
                if(attackSpeedChecked)
                    for(int i=0;i<shopClass.attackSpeed.size();i++)
                        customBuild.add(shopClass.attackSpeed.get(i));
                if(lifeStealChecked)
                    for(int i=0;i<shopClass.lifesteal.size();i++)
                        customBuild.add(shopClass.lifesteal.get(i));
                if(penetrationChecked)
                    for(int i=0;i<shopClass.penetration.size();i++)
                        customBuild.add(shopClass.penetration.get(i));
                if(critChanceChecked)
                    for(int i=0;i<shopClass.critChance.size();i++)
                        customBuild.add(shopClass.critChance.get(i));
            }
            if(defensiveChecked)
                for(int i=0;i<shopClass.defensive.size();i++)
                    customBuild.add(shopClass.defensive.get(i));
            else{
                if(physicalChecked)
                    for(int i=0;i<shopClass.physicalDef.size();i++)
                        customBuild.add(shopClass.physicalDef.get(i));
                if(magicalChecked)
                    for(int i=0;i<shopClass.magicalDef.size();i++)
                        customBuild.add(shopClass.magicalDef.get(i));
                if(healthChecked)
                    for(int i=0;i<shopClass.health.size();i++)
                        customBuild.add(shopClass.health.get(i));
                if(hp5Checked)
                    for(int i=0;i<shopClass.hp5.size();i++)
                        customBuild.add(shopClass.hp5.get(i));
            }
            if(utilityChecked)
                for(int i=0;i<shopClass.utility.size();i++)
                    customBuild.add(shopClass.utility.get(i));
            else{
                if(auraChecked)
                    for(int i=0;i<shopClass.aura.size();i++)
                        customBuild.add(shopClass.aura.get(i));
                if(movementChecked)
                    for(int i=0;i<shopClass.movement.size();i++)
                        customBuild.add(shopClass.movement.get(i));
                if(cooldownChecked)
                    for(int i=0;i<shopClass.cooldown.size();i++)
                        customBuild.add(shopClass.cooldown.get(i));
                if(manaChecked)
                    for(int i=0;i<shopClass.mana.size();i++)
                        customBuild.add(shopClass.mana.get(i));
                if(mp5Checked)
                    for(int i=0;i<shopClass.mp5.size();i++)
                        customBuild.add(shopClass.mp5.get(i));
            }
            if(customBuild.size() <= 0)
                return getBuild(god, shopClass.allItems);
            return getBuild(god, customBuild);
        }
    }

    public String getBuild(String god, ArrayList<String> buildItems){
        String godBuild = "";
        ArrayList<String> build = new ArrayList<>();
        ArrayList<String> relics = new ArrayList<>();
        String item = "";
        if(getGodType(god).equalsIgnoreCase("Physical")){
            int check = 0;
            // Get the Starter Item.
            if(getStarterItem){
                while(check < 1){
                    int r = (int)(Math.random()*(shopClass.starterItems.size()));
                    item = shopClass.starterItems.get(r);
                    if(!shopClass.magical.contains(item)){
                        godBuild += "Starter Item: " + item + "\n\n";
                        check++;
                    }
                }
                // Get the Relics.
                check = 0;
            }
            if(getRelics){
                godBuild += "Relics: ";
                while(check < 2){
                    int r = (int)(Math.random()*(shopClass.relics.size()));
                    item = shopClass.relics.get(r);
                    if(!shopClass.magical.contains(item) && !relics.contains(item)){
                        relics.add(item);
                        check++;
                    }
                }
                for(int i=0;i<relics.size();i++)
                    godBuild += relics.get(i) + ", ";
                godBuild = godBuild.substring(0,godBuild.length()-2) + "\n\n";
            }
            // Get the main build.
            int numOfBoots = 0;
            if(getBootsAlways){
                String boots = "";
                int b = (int)(Math.random()*(shopClass.boots.size()));
                boots = shopClass.boots.get(b);
                while(shopClass.magical.contains(boots)){
                    b = (int)(Math.random()*(shopClass.boots.size()));
                    boots = shopClass.boots.get(b);
                }
                build.add(boots);
                numOfBoots++;
            }
            int ran = 0;
            if(godClass.isUnique.contains(god)){
                ran = (int) (Math.random()*(shopClass.uniqueItems.size()));
                item = shopClass.uniqueItems.get(ran);
                build.add(item);
            }
            ArrayList<String> usable = new ArrayList<>();
            for(String s : buildItems){
                if(!shopClass.magical.contains(s))
                    usable.add(s);
            }
            int numOfItems = usable.size();
            while(build.size() < 6){
                int r = (int)(Math.random()*(usable.size()));
                if(usable.size() <= 0){
                    int rA = (int)(Math.random()*(shopClass.allItems.size()));
                    item = shopClass.allItems.get(rA);
                }
                else
                    item = usable.get(r);
                if(shopClass.boots.contains(item) && !getBootsAlways)
                    numOfBoots++;
                else
                    while(shopClass.boots.contains(item)){
                        r = (int)(Math.random()*(usable.size()));
                        item = usable.get(r);
                    }
                if(!shopClass.consumable.contains(item) && !shopClass.relics.contains(item) && !shopClass.starterItems.contains(item) && !shopClass.magical.contains(item) && !build.contains(item))
                    if(!shopClass.selfHeal.contains(item) || (shopClass.selfHeal.contains(item) && godClass.hasHeal.contains(god)))
                        if((!getBootsAlways && numOfBoots <= 1 ) || numOfBoots <= 1){
                            build.add(item);
                            numOfItems--;
                        }
                        else
                            numOfBoots = 1;
                if(numOfItems <= 0 || (movementChecked && numOfItems == 3))
                    for(int i=0;i<shopClass.allItems.size();i++)
                        usable.add(shopClass.allItems.get(i));
            }
        }
        else if(getGodType(god).equalsIgnoreCase("Magical")){
            int check = 0;
            // Get the Starter Item.
            if(getStarterItem){
                while(check < 1){
                    int r = (int)(Math.random()*(shopClass.starterItems.size()));
                    item = shopClass.starterItems.get(r);
                    if(!shopClass.physical.contains(item)){
                        godBuild += "Starter Item: " + item + "\n\n";
                        check++;
                    }
                }
                // Get the Relics.
                check = 0;
            }
            if(getRelics){
                godBuild += "Relics: ";
                while(check < 2){
                    int r = (int)(Math.random()*(shopClass.relics.size()));
                    item = shopClass.relics.get(r);
                    if(!shopClass.physical.contains(item) && !relics.contains(item)){
                        relics.add(item);
                        check++;
                    }
                }
                for(int i=0;i<relics.size();i++)
                    godBuild += relics.get(i) + ", ";
                godBuild = godBuild.substring(0,godBuild.length()-2) + "\n\n";
            }
            // Get the main build.
            int numOfBoots = 0;
            if(getBootsAlways){
                String boots = "";
                int b = (int)(Math.random()*(shopClass.boots.size()));
                boots = shopClass.boots.get(b);
                while(shopClass.physical.contains(boots)){
                    b = (int)(Math.random()*(shopClass.boots.size()));
                    boots = shopClass.boots.get(b);
                }
                build.add(boots);
                numOfBoots++;
            }
            ArrayList<String> usable = new ArrayList<>();
            for(String s : buildItems){
                if(!shopClass.physical.contains(s))
                    usable.add(s);
            }
            int numOfItems = usable.size();
            while(build.size() < 6){
                int r = (int)(Math.random()*(usable.size()));
                if(usable.size() <= 0){
                    int rA = (int)(Math.random()*(shopClass.allItems.size()));
                    item = shopClass.allItems.get(rA);
                }
                else
                    item = usable.get(r);
                if(shopClass.boots.contains(item) && !getBootsAlways)
                    numOfBoots++;
                else
                    while(shopClass.boots.contains(item)){
                        r = (int)(Math.random()*(usable.size()));
                        item = usable.get(r);
                    }
                if(!shopClass.consumable.contains(item) && !shopClass.relics.contains(item) && !shopClass.starterItems.contains(item) && !shopClass.physical.contains(item) && !build.contains(item))
                    if(!shopClass.selfHeal.contains(item) || (shopClass.selfHeal.contains(item) && godClass.hasHeal.contains(god)))
                        if((!getBootsAlways && numOfBoots <= 1 ) || numOfBoots <= 1){
                            build.add(item);
                            numOfItems--;
                        }
                        else
                            numOfBoots = 1;
                if(numOfItems <= 0 || (movementChecked && numOfItems == 3))
                    for(int i=0;i<shopClass.allItems.size();i++)
                        usable.add(shopClass.allItems.get(i));
            }
        }
        if(build.size() > 0){
            godBuild += "Build:\n";
            for(int i=0;i<build.size();i++)
                godBuild += build.get(i) + "\n";
            godBuild += "\nPress the 'Random' or 'Customize' button to randomize again!";
            return godBuild;
        }
        return "Error getting a build!";
    }

    public String getGod(){
        int r = (int)(Math.random()*(listOfGods.length-1))+1;
        if(!godClassList.getSelectedItem().toString().equalsIgnoreCase("Not Specified"))
            return getGodByClass(godClassList.getSelectedItem().toString());
        else if(!godTypeList.getSelectedItem().toString().equalsIgnoreCase("Not Specified"))
            return getGodByType(godTypeList.getSelectedItem().toString());
        else if(!godList.getSelectedItem().toString().equalsIgnoreCase("Not Specified"))
            return godList.getSelectedItem().toString();
        else
            return getGodByName(listOfGods[r]);
    }

    public String getGodByName(String god){
        return getGodByClass(getGodClass(god));
    }

    public String getGodByType(String type){
        if(type.equalsIgnoreCase("Physical")){
            return getGodNameByType(godClass.physicalGods);
        }
        else if(type.equalsIgnoreCase("Magical")){
            return getGodNameByType(godClass.magicalGods);
        }
        return "Error getting god type!";
    }

    public String getGodByClass(String c){
        if(c.equalsIgnoreCase("Assassin")){
            return getGodNameByClass(godClass.assassins);
        }
        else if(c.equalsIgnoreCase("Warrior")){
            return getGodNameByClass(godClass.warriors);
        }
        else if(c.equalsIgnoreCase("Guardian")){
            return getGodNameByClass(godClass.guardians);
        }
        else if(c.equalsIgnoreCase("Mage")){
            return getGodNameByClass(godClass.mages);
        }
        else if(c.equalsIgnoreCase("Hunter")){
            return getGodNameByClass(godClass.hunters);
        }
        return "Error getting god class!";
    }

    public String getGodNameByType(ArrayList<String> godTypeList){
        int r = (int) (Math.random()*(godTypeList.size()));
        return (godTypeList.get(r));
    }

    public String getGodNameByClass(ArrayList<String> godClassList){
        int r = (int) (Math.random()*(godClassList.size()));
        return (godClassList.get(r));
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

    public void actionPerformed(Object o){
        // Top drop down lists.
        if(o.toString().contains("godSpinner")){
            if(!godList.getSelectedItem().toString().equalsIgnoreCase("Not Specified")){
                godName = godList.getSelectedItem().toString();
                godTypeList.setEnabled(false);
                godClassList.setEnabled(false);
            }
            else{
                godTypeList.setEnabled(true);
                godClassList.setEnabled(true);
            }
        }
        if(o.toString().contains("godTypeSpinner")){
            if(!godTypeList.getSelectedItem().toString().equalsIgnoreCase("Not Specified")){
                godList.setEnabled(false);
                godClassList.setEnabled(false);
            }
            else{
                godList.setEnabled(true);
                godClassList.setEnabled(true);
            }
        }
        if(o.toString().contains("godClassSpinner")){
            if(!godClassList.getSelectedItem().toString().equalsIgnoreCase("Not Specified")){
                godList.setEnabled(false);
                godTypeList.setEnabled(false);
            }
            else{
                godList.setEnabled(true);
                godTypeList.setEnabled(true);
            }
        }
        // Bottom Check Boxes
        if(o.toString().contains("starterItemCheckBox"))
            getStarterItem = !getStarterItem;
        if(o.toString().contains("randomRelicsCheckBox"))
            getRelics = !getRelics;
        if(o.toString().contains("bootsCheckBox"))
            getBootsAlways = !getBootsAlways;
        // ---
        // Column 1:
        // ---
        if(o.toString().contains("offensiveCheckBox"))
            if(!offensiveChecked){
                offensiveChecked = true;
                power.setEnabled(false);
                powerChecked = false;
                attackSpeed.setEnabled(false);
                attackSpeedChecked = false;
                lifeSteal.setEnabled(false);
                lifeStealChecked = false;
                penetration.setEnabled(false);
                penetrationChecked = false;
                critChance.setEnabled(false);
                critChanceChecked = false;
            }
            else{
                offensiveChecked = false;
                power.setEnabled(true);
                if(power.isSelected())
                    powerChecked = true;
                attackSpeed.setEnabled(true);
                if(attackSpeed.isSelected())
                    attackSpeedChecked = true;
                lifeSteal.setEnabled(true);
                if(lifeSteal.isSelected())
                    lifeStealChecked = true;
                penetration.setEnabled(true);
                if(penetration.isSelected())
                    penetrationChecked = true;
                critChance.setEnabled(true);
                if(critChance.isSelected())
                    critChanceChecked = true;
            }
        if(o.toString().contains("powerCheckBox"))
            if(power.isEnabled())
                powerChecked = !powerChecked;
        if(o.toString().contains("attackSpeedCheckBox"))
            if(attackSpeed.isEnabled())
                attackSpeedChecked = !attackSpeedChecked;
        if(o.toString().contains("lifeStealCheckBox"))
            if(lifeSteal.isEnabled())
                lifeStealChecked = !lifeStealChecked;
        if(o.toString().contains("penetrationCheckBox"))
            if(penetration.isEnabled())
                penetrationChecked = !penetrationChecked;
        if(o.toString().contains("critChanceCheckBox"))
            if(critChance.isEnabled())
                critChanceChecked = !critChanceChecked;
        // ---
        // Column 2:
        // ---
        if(o.toString().contains("defensiveCheckBox")){
            if(!defensiveChecked){
                defensiveChecked = true;
                physical.setEnabled(false);
                physicalChecked = false;
                magical.setEnabled(false);
                magicalChecked = false;
                health.setEnabled(false);
                healthChecked = false;
                hp5.setEnabled(false);
                hp5Checked = false;
            }
            else{
                defensiveChecked = false;
                physical.setEnabled(true);
                if(physical.isSelected())
                    physicalChecked = true;
                magical.setEnabled(true);
                if(magical.isSelected())
                    magicalChecked = true;
                health.setEnabled(true);
                if(health.isSelected())
                    healthChecked = true;
                hp5.setEnabled(true);
                if(hp5.isSelected())
                    hp5Checked = true;
            }
        }
        if(o.toString().contains("physicalCheckBox"))
            if(physical.isEnabled())
                physicalChecked = !physicalChecked;
        if(o.toString().contains("magicalCheckBox"))
            if(magical.isEnabled())
                magicalChecked = !magicalChecked;
        if(o.toString().contains("healthCheckBox"))
            if(health.isEnabled())
                healthChecked = !healthChecked;
        if(o.toString().contains("hp5CheckBox"))
            if(hp5.isEnabled())
                hp5Checked = !hp5Checked;
        // ---
        // Column 3:
        // ---
        if(o.equals(utility)){
            if(!utilityChecked){
                utilityChecked = true;
                aura.setEnabled(false);
                auraChecked = false;
                movement.setEnabled(false);
                movementChecked = false;
                cooldown.setEnabled(false);
                cooldownChecked = false;
                mana.setEnabled(false);
                manaChecked = false;
                mp5.setEnabled(false);
                mp5Checked = false;
            }
            else{
                utilityChecked = false;
                aura.setEnabled(true);
                if(aura.isSelected())
                    auraChecked = true;
                movement.setEnabled(true);
                if(movement.isSelected())
                    movementChecked = true;
                cooldown.setEnabled(true);
                if(cooldown.isSelected())
                    cooldownChecked = true;
                mana.setEnabled(true);
                if(mana.isSelected())
                    manaChecked = true;
                mp5.setEnabled(true);
                if(mp5.isSelected())
                    mp5Checked = true;
            }
        }
        if(o.toString().contains("auraCheckBox"))
            if(aura.isEnabled())
                auraChecked = !auraChecked;
        if(o.toString().contains("movementCheckBox"))
            if(movement.isEnabled())
                movementChecked = !movementChecked;
        if(o.toString().contains("cooldownCheckBox"))
            if(cooldown.isEnabled())
                cooldownChecked = !cooldownChecked;
        if(o.toString().contains("manaCheckBox"))
            if(mana.isEnabled())
                manaChecked = !manaChecked;
        if(o.toString().contains("mp5CheckBox"))
            if(mp5.isEnabled())
                mp5Checked = !mp5Checked;
    }

    public void setListeners(){
        godList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                actionPerformed(godList);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        godTypeList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                actionPerformed(godTypeList);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        godClassList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                actionPerformed(godClassList);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Column 1:
        offensive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(offensive);
            }
        });
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(power);
            }
        });
        attackSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(attackSpeed);
            }
        });
        lifeSteal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(lifeSteal);
            }
        });
        penetration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(penetration);
            }
        });
        critChance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(critChance);
            }
        });
        // Column 2:
        defensive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(defensive);
            }
        });
        physical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(physical);
            }
        });
        magical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(magical);
            }
        });
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(health);
            }
        });
        hp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(hp5);
            }
        });
        // Column 3:
        utility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(utility);
            }
        });
        aura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(aura);
            }
        });
        movement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(movement);
            }
        });
        cooldown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(cooldown);
            }
        });
        mana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(mana);
            }
        });
        mp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(mp5);
            }
        });

        starterItemBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                actionPerformed(starterItemBox);    }
        });
        relicsBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(relicsBox);
            }
        });
        alwaysBoots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerformed(alwaysBoots);
            }
        });
    }
}
