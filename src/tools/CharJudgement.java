package tools;

import CharsAndStrings.Even;
import CharsAndStrings.Odd;

public class CharJudgement {
    public boolean isSpace(char ch){ return ch == ' '; }
    public boolean isNewline(char ch){ return ch == '\n' || ch == '\r'; }
    public boolean isEnter(char ch){ return ch == '\n'; }
    public boolean isTab(char ch){ return ch == '\t'; }
    public boolean isEmpty(char ch){ return ch == 0; }
    public boolean isLetter(char ch){ return ((ch>='A' && ch<='Z') || (ch>='a' && ch<='z') || (ch == '_')); }
    public boolean isDigit(char ch){ return ch>='0' && ch<='9' ;}
    public boolean isSlash(char ch){ return ch =='/'; }
    public boolean isStar(char ch){ return ch == '*'; }
    public boolean isOdd(char ch){
        for(int i=0;i<Odd.hold.length;i++){
            if(ch == Odd.hold[i]){
                return true;
            }
        }
        return false;
    }
    public boolean isEven(char ch){
        for(int i=0;i<Even.hold.length;i++){
            if(ch == Even.hold[i]){
                return true;
            }
        }
        return false;
    }
    public boolean isShuangyin(char ch){ return ch == '\"'; }
}
