package com.tutorials.hp.mysqlspinner.mMySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.tutorials.hp.mysqlspinner.mDataObject.Spacecraft;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Oclemy on 5/16/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class DataParser extends AsyncTask<Void,Void,Integer> {

    Context c;
    Spinner sp;
    String jsonData;

    ProgressDialog pd;
    ArrayList<String> spacecrafts=new ArrayList<>();

    public DataParser(Context c, Spinner sp, String jsonData) {
        this.c = c;
        this.sp = sp;
        this.jsonData = jsonData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


        pd=new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing...Please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        pd.dismiss();

        if(result==0)
        {
            Toast.makeText(c,"Unable To Parse",Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(c,"Parsed Successsfully",Toast.LENGTH_SHORT).show();

            //BIND
            ArrayAdapter adapter=new ArrayAdapter(c,android.R.layout.simple_list_item_1,spacecrafts);
            sp.setAdapter(adapter);

            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(c,spacecrafts.get(position),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

    private int parseData()
    {
        try {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;

            spacecrafts.clear();
            Spacecraft s=null;

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                int id=jo.getInt("id");
                String name=jo.getString("name");

                s=new Spacecraft();
                s.setId(id);
                s.setName(name);

                spacecrafts.add(name);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;

    }

}
