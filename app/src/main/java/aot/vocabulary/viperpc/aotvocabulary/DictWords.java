package aot.vocabulary.viperpc.aotvocabulary;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Viper PC on 3/10/2017.
 */
public class DictWords implements Serializable {

    private String word;
    private String pos;
    private String spofreq;
    private String wrifreq;
    private ArrayList<Meaning> meaningArraList = new ArrayList<>();

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getSpofreq() {
        return spofreq;
    }

    public void setSpofreq(String spofreq) {
        this.spofreq = spofreq;
    }

    public String getWrifreq() {
        return wrifreq;
    }

    public void setWrifreq(String wrifreq) {
        this.wrifreq = wrifreq;
    }

    public ArrayList<Meaning> getMeaningArraList() {
        return meaningArraList;
    }

    public void setMeaningArraList(ArrayList<Meaning> meaningArraList) {
        this.meaningArraList = meaningArraList;
    }
}
