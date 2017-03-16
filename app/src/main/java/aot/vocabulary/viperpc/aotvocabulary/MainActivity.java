package aot.vocabulary.viperpc.aotvocabulary;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView alphabets,sidewords;
    int alphanum=0;

    Map<String,ArrayList<DictWords>> wordMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alphabets=(ListView)findViewById(R.id.lvLetters);
        sidewords=(ListView)findViewById(R.id.lvWords);

        wordMap=new HashMap<>();
        List<String> list=new ArrayList<>();
        final String alphabet="abcdefghijklmnopqrstuvwxyz";

        for(int i=0;i<26;i++)
            list.add(alphabet.charAt(i)+"");


        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,list);
        alphabets.setAdapter(arrayAdapter);

        alphabets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  words.setText(alphabet.charAt(position)+"");
                String all = "";
                alphanum=position;
                ArrayList<DictWords> al = wordMap.get((alphabet.charAt(position) + "").toLowerCase());
                ArrayList<String> arrayList = new ArrayList<String>();
                for (DictWords s : al) {
                    arrayList.add(s.getWord());

                }
                final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, arrayList);
                sidewords.setAdapter(arrayAdapter2);

            }
        });

        sidewords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<DictWords> al = wordMap.get((alphabet.charAt(alphanum) + "").toLowerCase());
                DictWords dictWords=al.get(position);
                Intent intent=new Intent(getApplicationContext(),WordDetailsActiity.class);
                intent.putExtra("dictword",dictWords);
                startActivity(intent);
            }
        });

        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("dict.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            String entiredoc="";
            while((line = in.readLine()) != null) {
                //String[] word = line.split("-");
                entiredoc+=line+"\n";

            }
            JSONObject jsonObject=new JSONObject(entiredoc);
            JSONArray jo=jsonObject.getJSONArray("words");
            Log.d("test",jsonObject.toString());
            Log.d("count",jo.length()+"");

            for(int i=0;i<jo.length();i++)
            {
                JSONObject oneword=jo.getJSONObject(i);
                String word=oneword.getString("word");
                String pos=oneword.getString("pos");
                String spf=oneword.getString("spokenfreq");
                String qrf=oneword.getString("writtenfreq");

                JSONArray jArray=oneword.getJSONArray("meaning");
                ArrayList<Meaning> aMean=new ArrayList<>();
                for(int j=0;j<jArray.length();j++)
                {
                    JSONObject onemeaning=jArray.getJSONObject(j);
                    String mean=onemeaning.getString("mean");
                    String syno=onemeaning.getString("synonyms");
                    String sent=onemeaning.getString("sentences");
                    String antonym=onemeaning.getString("antonyms");
                    Meaning meaning=new Meaning();
                    meaning.setMean(mean);
                    meaning.setSynonyms(syno);
                    meaning.setSentences(sent);
                    meaning.setAntonyms(antonym);
                    aMean.add(meaning);
                }
                DictWords dictWords=new DictWords();
                dictWords.setWord(word);
                dictWords.setSpofreq(spf);
                dictWords.setWrifreq(qrf);
                dictWords.setPos(pos);
                dictWords.setMeaningArraList(aMean);

                if(wordMap.containsKey((word.charAt(0)+"").toLowerCase()))
                {
                    ArrayList<DictWords> al=wordMap.get((word.charAt(0)+"").toLowerCase());
                    al.add(dictWords);
                }
                else {
                    ArrayList <DictWords> al= new ArrayList<>();
                    al.add(dictWords);
                    wordMap.put((word.charAt(0) + "").toLowerCase(), al);
                }
               // Log.d("parsed",word);*/
            }

            Log.d("vocab",entiredoc);
            //Log.d("dict value",hashMap.get("logout"));
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
