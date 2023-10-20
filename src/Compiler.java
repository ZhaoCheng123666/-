import CharsAndStrings.Reserved;
import CharsAndStrings.SavedWords;
import CharsAndStrings.Word;
import Modules.ParseCompiler;
import Modules.SymbolCompiler;
import Modules.SymbolCompiler0;

import java.io.*;

public class Compiler {
    public static File input = new File("testfile.txt");

    public void compile(File input) throws IOException {
        SavedWords savedWords = new SavedWords();
        SymbolCompiler symbolCompiler = new SymbolCompiler(input, savedWords);
        while(true){
            int symbol = SymbolCompiler.getSym();
            if(symbol == -1) break;
            if(symbol == -2) continue;
            savedWords.add(SymbolCompiler.symbol, SymbolCompiler.token);//将每一个读得的单词存入到SavedWords中
        }

        ParseCompiler parseCompiler = new ParseCompiler(savedWords);
        ParseCompiler.CompUnit();

    }
    public static void main(String[] args) throws IOException {
        Compiler compiler = new Compiler();
        compiler.compile(input);
    }
}
