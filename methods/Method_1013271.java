public static CToken[][] Tokenize(Vector vec){
  if ((vec == null) || (vec.size() == 0)) {
    return null;
  }
  ;
  argVec=vec;
  line=0;
  if (argVec.elementAt(line) == null) {
    curString="";
  }
 else {
    curString=(String)argVec.elementAt(line);
  }
  ;
  if (curString.equals("")) {
    nextChar='\n';
  }
 else {
    nextChar=curString.charAt(0);
  }
  ;
  col=0;
  ncol=0;
  char repChar=' ';
  state=START;
  while (state != DONE) {
switch (state) {
case START:
      if (Misc.IsSpace(nextChar)) {
        skipNextChar();
        gotoStart();
      }
 else       if (Misc.IsLetter(nextChar)) {
        addNextChar();
        state=ID;
      }
 else       if (Misc.IsDigit(nextChar)) {
        addNextChar();
        state=NUM_OR_ID;
      }
 else       if (nextChar == '\n') {
        skipNextChar();
        startNewLine();
        gotoStart();
      }
 else       if (nextChar == '\\') {
        addNextChar();
        state=BS;
      }
 else       if (FormatComments.isRepeatChar(nextChar)) {
        repChar=nextChar;
        addNextChar();
        state=REPEAT_CHAR;
      }
 else       if (nextChar == '"') {
        if (inDQuote) {
          addNextChar();
          state=RIGHT_DQUOTE;
        }
 else {
          skipNextChar();
          state=STRING;
        }
        ;
      }
 else       if (nextChar == '`') {
        addNextChar();
        state=LEFT_SQUOTE;
      }
 else       if (nextChar == '\'') {
        if (inSQuote) {
          skipNextChar();
          inSQuote=false;
          gotoStart();
        }
 else {
          addNextChar();
          state=RIGHT_SQUOTE;
        }
        ;
      }
 else       if (BuiltInSymbols.IsBuiltInPrefix("" + nextChar)) {
        addNextChar();
        state=BUILT_IN;
      }
 else       if (nextChar == '\t') {
        state=DONE;
      }
 else {
        addNextChar();
        CTokenOut(CToken.OTHER);
        gotoStart();
      }
    ;
  break;
case ID:
if ((token.length() == 3) && (token.equals("WF_") || token.equals("SF_"))) {
  CTokenOut(CToken.BUILTIN);
  gotoStart();
}
 else if (Misc.IsLetter(nextChar) || Misc.IsDigit(nextChar)) {
  addNextChar();
}
 else if (BuiltInSymbols.IsBuiltInSymbol(token)) {
  CTokenOut(CToken.BUILTIN);
  gotoStart();
}
 else {
  if (isAllUnderscores()) {
    CTokenOut(CToken.REP_CHAR);
  }
 else {
    CTokenOut(CToken.IDENT);
  }
  ;
  gotoStart();
}
;
break;
case NUM_OR_ID:
if (Misc.IsDigit(nextChar)) {
addNextChar();
state=NUM_OR_ID;
}
 else if (Misc.IsLetter(nextChar)) {
addNextChar();
state=ID;
}
 else {
CTokenOut(CToken.NUMBER);
gotoStart();
}
break;
case BS:
if ((nextChar == 'b') || (nextChar == 'B') || (nextChar == 'o') || (nextChar == 'O') || (nextChar == 'h') || (nextChar == 'H')) {
addNextChar();
state=NUM_OR_BI;
}
 else if (Misc.IsLetter(nextChar)) {
state=BSBUILT_IN;
}
 else {
state=BUILT_IN;
}
break;
case NUM_OR_BI:
if (Misc.IsDigit(nextChar)) {
state=NUM;
}
 else {
state=BSBUILT_IN;
}
;
break;
case NUM:
if (Misc.IsDigit(nextChar)) {
addNextChar();
state=NUM;
}
 else if (Misc.IsLetter(nextChar) && (token.charAt(0) != '\\')) {
addNextChar();
state=ID;
}
 else {
CTokenOut(CToken.NUMBER);
gotoStart();
}
break;
case BSBUILT_IN:
if (Misc.IsLetter(nextChar)) {
addNextChar();
state=BSBUILT_IN;
}
 else if (BuiltInSymbols.IsBuiltInSymbol(token)) {
CTokenOut(CToken.BUILTIN);
gotoStart();
}
 else {
CTokenOut(CToken.OTHER);
gotoStart();
}
;
break;
case BUILT_IN:
if (BuiltInSymbols.IsBuiltInPrefix(token + nextChar)) {
addNextChar();
}
 else {
if (!BuiltInSymbols.IsBuiltInSymbol(token)) {
while (!BuiltInSymbols.IsBuiltInSymbol(token)) {
Backspace(1);
if (token.length() == 0) {
Debug.ReportBug("Error tokenizing built-in symbol");
}
;
token=token.substring(0,token.length() - 1);
}
;
nextChar=curString.charAt(ncol);
}
;
CTokenOut(CToken.BUILTIN);
gotoStart();
}
break;
case REPEAT_CHAR:
if (nextChar == repChar) {
addNextChar();
}
 else if (token.length() >= FormatComments.getRepeatCharMin(repChar)) {
CTokenOut(CToken.REP_CHAR);
state=START;
}
 else {
state=BUILT_IN;
}
break;
case STRING:
if (nextChar == '\\') {
addNextChar();
state=ESC_STRING;
}
 else if (BuiltInSymbols.IsStringChar(nextChar)) {
addNextChar();
state=STRING;
}
 else if ((nextChar == '"') && (TokenizeSpec.isString(token) || inSQuote)) {
skipNextChar();
CTokenOut(CToken.STRING);
gotoStart();
}
 else {
state=LEFT_DQUOTE;
}
break;
case ESC_STRING:
if ((nextChar == '\"') || (nextChar == '\\') || (nextChar == 't') || (nextChar == 'n') || (nextChar == 'f') || (nextChar == 'r')) {
addNextChar();
state=STRING;
}
 else {
state=LEFT_DQUOTE;
}
;
break;
case LEFT_DQUOTE:
Backspace(token.length() + 1);
token="\"";
CTokenOut(CToken.LEFT_DQUOTE);
inDQuote=true;
skipNextChar();
gotoStart();
break;
case RIGHT_DQUOTE:
CTokenOut(CToken.RIGHT_DQUOTE);
inDQuote=false;
gotoStart();
break;
case LEFT_SQUOTE:
if (nextChar == '`') {
addNextChar();
CTokenOut(CToken.OTHER);
gotoStart();
}
 else if (nextChar == '^') {
skipNextChar();
token="  ";
state=TEX;
}
 else if (nextChar == '~') {
skipNextChar();
token="  ";
state=THROW_AWAY;
}
 else if (nextChar == '.') {
skipNextChar();
token="  ";
state=VERB;
}
 else {
token="";
inSQuote=true;
col=ncol - 1;
state=START;
}
break;
case RIGHT_SQUOTE:
if (nextChar == '\'') {
addNextChar();
CTokenOut(CToken.OTHER);
gotoStart();
}
 else {
state=BUILT_IN;
}
break;
case TEX:
if (nextChar == '^') {
skipNextChar();
state=TEX_CARET;
}
 else if (nextChar == '\n') {
skipNextChar();
if (token.equals("")) {
token=" ";
}
CTokenOut(CToken.TEX);
startNewLine();
}
 else if (nextChar == '\t') {
if (!Misc.isBlank(token)) {
CTokenOut(CToken.TEX);
}
;
token="";
state=DONE;
}
 else {
addNextChar();
}
break;
case TEX_CARET:
if (nextChar == '\'') {
skipNextChar();
if (!Misc.isBlank(token)) {
CTokenOut(CToken.TEX);
}
;
token="";
gotoStart();
}
 else {
token=token + "^";
state=TEX;
}
break;
case THROW_AWAY:
if (nextChar == '~') {
skipNextChar();
state=THROW_AWAY_TILDE;
}
 else if (nextChar == '\n') {
skipNextChar();
if (linev.size() != 0) {
startNewLine();
}
col=0;
ncol=0;
}
 else if (nextChar == '\t') {
state=DONE;
}
 else {
addNextChar();
}
break;
case THROW_AWAY_TILDE:
if (nextChar == '\'') {
skipNextChar();
token="";
gotoStart();
}
 else {
skipNextChar();
state=THROW_AWAY;
}
break;
case VERB:
if (nextChar == '.') {
skipNextChar();
state=VERB_DOT;
}
 else if (nextChar == '\n') {
skipNextChar();
if (!Misc.isBlank(token)) {
CTokenOut(CToken.VERB);
}
;
token="";
startNewLine();
}
 else if (nextChar == '\t') {
if (!Misc.isBlank(token)) {
CTokenOut(CToken.VERB);
}
;
state=DONE;
}
 else {
addNextChar();
}
break;
case VERB_DOT:
if (nextChar == '\'') {
skipNextChar();
CTokenOut(CToken.VERB);
gotoStart();
}
 else {
token=token + ".";
state=VERB;
}
break;
default :
Debug.ReportBug("Unexpected state in comment-parsing algorithm");
}
;
}
;
return vspecToArray();
}
