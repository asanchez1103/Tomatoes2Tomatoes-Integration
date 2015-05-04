package t2t.tomatoes2tomatoes;

/**
 * Created by Alejandro on 5/3/2015.
 */

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import android.content.Context;

import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader; // android compatible
import java.io.InputStreamReader;
import java.io.*;

public class ReadWithScanner extends CardClass{


    // PRIVATE
    private Context _ctx;
    private  int _resId;


    public ReadWithScanner(Context ctx, int resId, int startId, String theName, String theInfo, int theHolder, boolean pubYet){
        super(startId, theName, theInfo, theHolder, pubYet);
        _ctx = ctx;
        _resId = resId;
    }


    public  String[][] processLineByLine(String[][] cardsArray) throws IOException {
        //  try (Scanner scanner =  new Scanner(fFilePath, ENCODING.name())){
    /*android compatible*/
        try{
            InputStream inputStream = _ctx.getResources().openRawResource(_resId);
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            int loc=0;
            String[] currArr = new String[2];
            String line; // new
            // while (scanner.hasNextLine()){
            // currArr = processLine(scanner.nextLine(),currArr);
    	/*android compatible*/
            while((line = bufferedReader.readLine()) !=null){
                currArr = processLine(line,currArr);
                cardsArray[loc][0] = currArr[0];
                cardsArray[loc][1] = currArr[1];
                loc++;
            }
            bufferedReader.close();
        }
        finally{}
        return cardsArray;
    } // end processLineByLine()

    protected String[] processLine(String aLine, String[] strArr){
        //use a Scanner to parse the content of each line
        Scanner sep = new Scanner(aLine);
        sep.useDelimiter(":: ");
        if (sep.hasNext()){
            //assumes the line has a certain structure
            String name = sep.next();
            String info = sep.next();
            strArr[0] = name;
            strArr[1] = info;
            sep.close();
            return strArr;
        }
        else {
            strArr[0] = "";
            strArr[1] = "";
            sep.close();
            return strArr;
        }
    } // end processLine()
}
