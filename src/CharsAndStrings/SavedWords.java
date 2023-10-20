package CharsAndStrings;

import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

import java.util.ArrayList;

public class SavedWords {
    public ArrayList<Word> words = new ArrayList<>();
    public int count = 0;
    public void add(int symbol, String token){
        words.add(new Word(symbol, token));
    }
}
