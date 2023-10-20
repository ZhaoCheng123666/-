import CharsAndStrings.Reserved;
import Modules.SymbolCompiler;

import java.io.*;

public class Compiler {
    public static File input = new File("testfile.txt");
    public static File output = new File("output.txt");

    public void compile(File input, File output) throws IOException {
        SymbolCompiler symbolCompiler = new SymbolCompiler(input);
        FileWriter writer = new FileWriter(output);

        while (true){
            int symbol = symbolCompiler.getSym();
            if(symbol == -1) break;
            if(symbol == -2) continue;

            System.out.printf("%s %s\n", Reserved.hold[symbol][1], symbolCompiler.token);
            writer.write(Reserved.hold[symbol][1] + ' ' + symbolCompiler.token);
            writer.write('\n');
        }
        writer.close();
    }
    public static void main(String[] args) throws IOException {
        try{
            output.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        Compiler compiler = new Compiler();
        compiler.compile(input, output);
    }
}
