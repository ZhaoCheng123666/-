package Modules;

import CharsAndStrings.Reserved;
import tools.CharJudgement;
import tools.MyRead;

import java.io.File;

public class SymbolCompiler0 {
    public File file;
    public int resultValue, symbol, num, position;
    public String token, fileString;
    public Character ch;
    public CharJudgement charJudgement;


    public SymbolCompiler0(File testfile){
        this.file = testfile;
        position = 0;
        fileString = MyRead.readFileToString(file.toString());
        System.out.println(fileString);
        ch = fileString.charAt(position);
        charJudgement = new CharJudgement();
    }
    public void clearToken(){
        this.token = "";
    }
    public void retract(){ position--; ch = fileString.charAt(position); }
    public int reserver(){
        for(int i=0;i<Reserved.hold.length;i++) if(token.equals(Reserved.hold[i][0])) return i;
        return 0;
    }
    public void next(){
        token += ch;
        position++;
        if(position >= fileString.length()) return ;
        ch = fileString.charAt(position);
    }
    public void nextJump(){
        position++;
        if(position >= fileString.length()) return ;
        ch = fileString.charAt(position);
    }

    public int getSym(){
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
