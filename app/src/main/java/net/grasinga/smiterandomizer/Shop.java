package net.grasinga.smiterandomizer;

import android.content.res.Resources;

import java.io.*;
import java.util.ArrayList;

public class Shop
{
    // Gets the app's resources through the constructor.
    Resources resources;

    // Shop Lists
    public ArrayList<String> allItems = new ArrayList<>();
    public ArrayList<String> consumable = new ArrayList<>();
    public ArrayList<String> relics = new ArrayList<>();
    public ArrayList<String> starterItems = new ArrayList<>();
    public ArrayList<String> power = new ArrayList<>();
    public ArrayList<String> attackSpeed = new ArrayList<>();
    public ArrayList<String> lifesteal = new ArrayList<>();
    public ArrayList<String> penetration = new ArrayList<>();
    public ArrayList<String> critChance = new ArrayList<>();
    public ArrayList<String> physicalDef = new ArrayList<>();
    public ArrayList<String> magicalDef = new ArrayList<>();
    public ArrayList<String> health = new ArrayList<>();
    public ArrayList<String> hp5 = new ArrayList<>();
    public ArrayList<String> aura = new ArrayList<>();
    public ArrayList<String> movement = new ArrayList<>();
    public ArrayList<String> cooldown = new ArrayList<>();
    public ArrayList<String> mana = new ArrayList<>();
    public ArrayList<String> mp5 = new ArrayList<>();
    public ArrayList<String> offensive = new ArrayList<>();
    public ArrayList<String> defensive = new ArrayList<>();
    public ArrayList<String> utility = new ArrayList<>();
    public ArrayList<String> physical = new ArrayList<>();
    public ArrayList<String> magical = new ArrayList<>();
    public ArrayList<String> meleeOnly = new ArrayList<>();
    public ArrayList<String> selfHeal = new ArrayList<>();
    public ArrayList<String> boots = new ArrayList<>();
    public ArrayList<String> uniqueItems = new ArrayList<>();

    public Shop(Resources r){resources = r; populateShops();}

    private void populateShops(){
        populateShop(allItems, R.raw.all);
        populateShop(consumable, R.raw.consumable);
        populateShop(relics, R.raw.relics);
        populateShop(starterItems, R.raw.starteritems);
        populateShop(power, R.raw.power);
        populateShop(attackSpeed, R.raw.attackspeed);
        populateShop(lifesteal, R.raw.lifesteal);
        populateShop(penetration, R.raw.penetration);
        populateShop(critChance, R.raw.critchance);
        populateShop(physicalDef, R.raw.physicaldef);
        populateShop(magicalDef, R.raw.magicaldef);
        populateShop(health, R.raw.health);
        populateShop(hp5, R.raw.hp5);
        populateShop(aura, R.raw.aura);
        populateShop(movement, R.raw.movement);
        populateShop(cooldown, R.raw.cooldown);
        populateShop(mana, R.raw.mana);
        populateShop(mp5, R.raw.mp5);
        populateShop(offensive, R.raw.offensive);
        populateShop(defensive, R.raw.defensive);
        populateShop(utility, R.raw.utility);
        populateShop(physical, R.raw.physical);
        populateShop(magical, R.raw.magical);
        populateShop(meleeOnly, R.raw.meleeonly);
        populateShop(selfHeal, R.raw.selfheal);
        populateShop(boots, R.raw.boots);
        populateShop(uniqueItems, R.raw.uniqueitems);
    }

    private void populateShop(ArrayList<String> myArray, int file){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(resources.openRawResource(file))))
        {
            String line = br.readLine();
            while(line != null) {
                myArray.add(line);
                line = br.readLine();
            }
        }
        catch(IOException e){}
    }
}

