package CharsAndStrings;

public class Word {
    public String identifier;//标识符
    public String word;//单词
    public Word(int symbol, String word){
        this.identifier = Reserved.hold[symbol][1];
        this.word = word;
    }
}
