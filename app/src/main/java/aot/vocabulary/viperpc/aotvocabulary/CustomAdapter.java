package aot.vocabulary.viperpc.aotvocabulary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Viper PC on 3/10/2017.
 */
public class CustomAdapter extends ArrayAdapter<Meaning> {

    Context con;

    public CustomAdapter(Context context, List<Meaning> meanings) {
        super(context,R.layout.listviewcontents, meanings);
        con=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View view = layoutInflater.inflate(R.layout.listviewcontents, parent, false);

        TextView mean= (TextView) view.findViewById(R.id.tvwordmeaning);
        TextView syn= (TextView) view.findViewById(R.id.tvSynonyms);
        TextView sent= (TextView) view.findViewById(R.id.tvSentences);
        TextView ant= (TextView) view.findViewById(R.id.tvAntonyms);


        Meaning meaning=getItem(position);
        int pp=position+1;
        mean.setText(pp+". "+meaning.getMean());
        String sy[]=meaning.getSentences().split("\\$");
        String b="";
        for(String s: sy)
            b+=s + "\n\n";
        sent.setText(b);
        syn.setText(meaning.getSynonyms());

        String an[]=meaning.getAntonyms().split("\\$");
        String a="";
        for(String s: an)
            a+=s + "\n";
        ant.setText(a);

        return view;
    }
}
