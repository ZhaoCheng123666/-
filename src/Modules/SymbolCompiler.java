package Modules;

import CharsAndStrings.Reserved;
import CharsAndStrings.SavedWords;
import tools.CharJudgement;
import tools.MyRead;

import java.io.File;

public class SymbolCompiler {
    public static File file;
    public static int resultValue, symbol, num, position;
    public static String token, fileString;
    public static Character ch;
    public static CharJudgement charJudgement;
    public static SavedWords savedWords;


    public SymbolCompiler(){}
    public SymbolCompiler(File testfile, SavedWords savedWords){
        file = testfile;
        position = 0;
        fileString = MyRead.readFileToString(file.toString());
//        System.out.println(fileString);
        ch = fileString.charAt(position);
        charJudgement = new CharJudgement();
        SymbolCompiler.savedWords = savedWords;
    }
    public static void clearToken(){
        token = "";
    }
    public static void retract(){ position--; ch = fileString.charAt(position); }
    public static int reserver(){
        for(int i = 0; i< Reserved.hold.length; i++) if(token.equals(Reserved.hold[i][0])) return i;
        return 0;
    }
    public static void next(){
        token += ch;
        position++;
        if(position >= fileString.length()) return ;
        ch = fileString.charAt(position);
    }
    public static void nextJump(){
        position++;
        if(position >= fileString.length()) return ;
        ch = fileString.charAt(position);
    }
    public static void addWord(int symbol, String token){
        savedWords.add(symbol, token);
    }

    public static int getSym(){
        clearToken();
        while(charJudgement.isSpace(ch) || charJudgement.isNewline(ch) || charJudgement.isTab(ch) || charJudgement.isEmpty(ch)){
            nextJump();
            if(position >= fileString.length()){
                return -1;
            }
        }
        if(position >= fileString.length()) return -1;
        else if(charJudgement.isLetter(ch)){
            while(charJudgement.isLetter(ch) || charJudgement.isDigit(ch)){
                next();
            }
            resultValue = reserver();
            if(resultValue == 0) symbol = 0;
            else symbol = resultValue;
        }
        else if(charJudgement.isDigit(ch)){
            while (charJudgement.isDigit(ch)){
                next();
            }
            num = Integer.parseInt(token);
            symbol = 1;
        }
        else if(charJudgement.isOdd(ch)){
            if(!charJudgement.isSlash(ch)){
                next();
                symbol = reserver();
            }
            else {
                next();
                if(charJudgement.isSlash(ch)){
                    clearToken();
                    while(!charJudgement.isEnter(ch)){
                        nextJump();
                    }
                    symbol = -2;
                }
                else if(charJudgement.isStar(ch)){
                    clearToken();
                    nextJump();
                    while(true){
                        if(!charJudgement.isStar(ch)){
                            nextJump();
                            continue;
                        }
                        nextJump();
                        if(!charJudgement.isSlash(ch)){
                            continue;
                        }
                        else {
                            nextJump();
                            break;
                        }
                    }
                    symbol = -2;
                }
                else {
//                    retract();
                    symbol = reserver();
                }
            }
        }
        else if(charJudgement.isEven(ch)){
            if(ch == '&'){
                next();
                next();
                symbol = reserver();
            }
            else if(ch == '|'){
                next();
                next();
                symbol = reserver();
            }
            else if(ch == '<'){
                next();
                if(ch == '=') {
                    next();
                    symbol = reserver();
                }
                else symbol = reserver();
            }
            else if(ch == '>'){
                next();
                if(ch == '=') {
                    next();
                    symbol = reserver();
                }
                else symbol = reserver();
            }
            else if(ch == '='){
                next();
                if(ch == '=') {
                    next();
                    symbol = reserver();
                }
                else symbol = reserver();
            }
            else if(ch == '!'){
                next();
                if(ch == '=') {
                    next();
                    symbol = reserver();
                }
                else symbol = reserver();
            }
        }
        else if(charJudgement.isShuangyin(ch)){
            next();
            while(!charJudgement.isShuangyin(ch)){
                next();
            }
            next();
            symbol = 2;
        }
        return symbol;
    }
}
