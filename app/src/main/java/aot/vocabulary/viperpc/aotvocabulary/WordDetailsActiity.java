package aot.vocabulary.viperpc.aotvocabulary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WordDetailsActiity extends AppCompatActivity {

    TextView mainword,pos,spf,wrf,tv;
    ListView details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_details_actiity);
        mainword=(TextView)findViewById(R.id.tvTheWord);
        pos=(TextView)findViewById(R.id.tvPos);
        spf=(TextView)findViewById(R.id.tvSpf);
        wrf=(TextView)findViewById(R.id.tvWrf);
        details=(ListView)findViewById(R.id.lvWordMeaning);

        DictWords dictWords= (DictWords) getIntent().getSerializableExtra("dictword");
        mainword.setText(dictWords.getWord());
        pos.setText("[ "+dictWords.getPos()+" ]");
        spf.setText("[ "+dictWords.getSpofreq()+" ]");
        wrf.setText("[ "+dictWords.getWrifreq()+" ]");
       // tv.setText(dictWords.getMeaningArraList().get(0).getMean());

        ListAdapter listAdapter = new CustomAdapter(getApplicationContext(), dictWords.getMeaningArraList());
        details.setAdapter(listAdapter);


    }
}
