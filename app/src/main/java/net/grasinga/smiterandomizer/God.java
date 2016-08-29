package net.grasinga.smiterandomizer;

import android.content.res.Resources;

import java.io.*;
import java.util.ArrayList;

public class God
{
    // Gets the app's resources through the constructor.
    Resources resources;

    //God Lists
    public ArrayList<String> gods = new ArrayList<>();
    public ArrayList<String> physicalGods = new ArrayList<>();
    public ArrayList<String> magicalGods = new ArrayList<>();
    public ArrayList<String> assassins = new ArrayList<>();
    public ArrayList<String> warriors = new ArrayList<>();
    public ArrayList<String> guardians = new ArrayList<>();
    public ArrayList<String> mages = new ArrayList<>();
    public ArrayList<String> hunters = new ArrayList<>();
    public ArrayList<String> hasHeal = new ArrayList<>();
    public ArrayList<String> isUnique = new ArrayList<>();


    public God(Resources r){resources = r; populateGods();}

    private void populateGods(){
        populateGod(gods, R.raw.gods);
        populateGod(physicalGods, R.raw.physicalgods);
        populateGod(magicalGods, R.raw.magicalgods);
        populateGod(assassins, R.raw.assassins);
        populateGod(warriors, R.raw.warriors);
        populateGod(guardians, R.raw.guardians);
        populateGod(mages, R.raw.mages);
        populateGod(hunters, R.raw.hunters);
        populateGod(hasHeal, R.raw.heal);
        populateGod(isUnique, R.raw.unique);
    }

    private void populateGod(ArrayList<String> myArray, int file){
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

