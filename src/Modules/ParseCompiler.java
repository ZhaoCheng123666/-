package Modules;

import CharsAndStrings.SavedWords;

import java.io.FileWriter;
import java.io.IOException;

public class ParseCompiler {
    public static FileWriter writer;
    public static SavedWords savedWords;
    public static int position = 0;
    public ParseCompiler(SavedWords savedWords) throws IOException {
        ParseCompiler.savedWords = savedWords;
        writer = new FileWriter("output.txt");
    }
    public static String myGet(int position){
        return savedWords.words.get(position).word;
    }
    public static String myGetIden(int position){
        return savedWords.words.get(position).identifier;
    }
    public static void filePrint() throws IOException {
        writer.write(myGetIden(position) + " " + myGet(position) + "\n");
        System.out.println(myGetIden(position) + " " + myGet(position));
    }
    public static void CompUnit() throws IOException {
        while(true){
            int decl = Decl();
            if(decl==0) break;
        }
        while(true){
            int funcDef = FuncDef();
            if(funcDef == 0) break;
        }
        MainFuncDef();
        writer.close();
    }
    public static int Decl(){
        return 0;
    }
    public static int FuncDef(){
        return 0;
    }
    public static int MainFuncDef() throws IOException {
        if(myGet(position).equals("int")){
            filePrint();
            position++;
        }else return 0;
        if(myGet(position).equals("main")){
            filePrint();
            position++;
        }else return 0;
        if(myGet(position).equals("(")){
            filePrint();
            position++;
        }else return 0;
        if(myGet(position).equals(")")){
            filePrint();
            position++;
        }else return 0;
        if(Block()==1){
            writer.write("<MainFuncDef>\n");
            System.out.println("<MainFuncDef>");
            return 1;
        }else return 0;
    }
    public static int Block() throws IOException {
        if(myGet(position).equals("{")){
            filePrint();
            position++;
        }else return 0;
        if(myGet(position).equals("}")){
            filePrint();
            writer.write("<Block>\n");
            System.out.println("<Block>");
            position++;
            return 1;
        }else return 0;
    }
}
