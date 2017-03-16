package aot.vocabulary.viperpc.aotvocabulary;

import java.io.Serializable;

/**
 * Created by Viper PC on 3/10/2017.
 */
public class Meaning implements Serializable{
    private String mean;
    private String synonyms;
    private String sentences;
    private String antonyms;

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getSentences() {
        return sentences;
    }

    public void setSentences(String sentences) {
        this.sentences = sentences;
    }

    public String getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(String antonyms) {
        this.antonyms = antonyms;
    }
}
