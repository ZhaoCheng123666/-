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
    public static void getNext(){
        position++;
        if(savedWords.words.size()==position){
            savedWords.words.add(SymbolCompiler.getSym());
        }
        token = savedWords.words.get(position);
    }
    public static void retract(int step){
        position -= step;
    }
    public static void filePrint() throws IOException {
        writer.write(myGetIden(position) + " " + myGet(position) + "\n");
        System.out.println(myGetIden(position) + " " + myGet(position));
    }
    public static void myPrint(int startPos, int endPos) throws IOException {
        for(int i = startPos;i<=endPos;i++){
            writer.write(myGetIden(i) + " " + myGet(i) + "\n");
            System.out.println(myGetIden(i) + " " + myGet(i));
        }
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
            position++;
        }else return 0;
        if(myGet(position).equals("main")){
            position++;
        }else return 0;
        if(myGet(position).equals("(")){
            position++;
        }else return 0;
        if(myGet(position).equals(")")){
            position++;
        }else return 0;
        if(Block()==1){
            writer.write("<MainFuncDef>\n");
            System.out.println("<MainFuncDef>");
            return 1;
        }else return 0;
    }
    public static int Block() throws IOException {
        int startPos = position;
        if(myGet(position).equals("{")){
            position++;
        }else return 0;
        if(myGet(position).equals("}")){
            myPrint(startPos, position);
            writer.write("<Block>\n");
            System.out.println("<Block>");
            position++;
            return 1;
        }else return 0;
    }
}
