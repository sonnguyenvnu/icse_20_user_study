public static Token[][] Tokenize(CharReader charReader,int mode){
  vspec=new Vector(1000,1000);
  reader=charReader;
  nextChar=reader.getNextChar();
  hasPcal=false;
  isCSyntax=false;
  pcalStart=null;
  pcalEnd=null;
  inPcal=false;
  canBeLabel=false;
  pseudoCom=false;
  ORCom=false;
  int braceDepth=0;
switch (mode) {
case MODULE:
    state=PROLOG;
  break;
case TLA:
state=START;
break;
case PLUSCAL:
state=START;
hasPcal=true;
pcalStart=new Position(0,0);
inPcal=true;
canBeLabel=true;
isCSyntax=true;
break;
case P_PLUSCAL:
state=START;
hasPcal=true;
pcalStart=new Position(0,0);
inPcal=true;
canBeLabel=true;
break;
default :
Debug.ReportBug("TokenizeSpec.Tokenize called with illegal mode");
}
;
while (state != DONE) {
switch (state) {
case START:
if (Misc.IsSpace(nextChar)) {
skipNextChar();
gotoStart();
}
 else if (Misc.IsLetter(nextChar)) {
addNextChar();
state=ID;
}
 else if (Misc.IsDigit(nextChar)) {
addNextChar();
state=NUM_OR_ID;
}
 else if (nextChar == '\\') {
addNextChar();
state=BS;
}
 else if (nextChar == '-') {
addNextChar();
state=DASH1;
}
 else if (nextChar == '=') {
addNextChar();
state=EQ1;
}
 else if (nextChar == '(') {
skipNextChar();
state=LEFT_PAREN;
}
 else if (nextChar == '"') {
skipNextChar();
state=STRING;
}
 else if (nextChar == '\n') {
skipNextChar();
startNewLine();
gotoStart();
}
 else if (BuiltInSymbols.IsBuiltInPrefix("" + nextChar,inPcal)) {
addNextChar();
if (!(inPcal && (mode == MODULE))) {
state=BUILT_IN;
}
 else if (token.equals("*") && (nextChar == ')')) {
pcalEnd=getNextTokenPosition();
inPcal=false;
token="";
gotoStart();
}
 else if (isCSyntax) {
if (token.equals("{")) {
TokenOut(Token.BUILTIN);
canBeLabel=true;
braceDepth++;
gotoStart();
}
 else if (token.equals("}")) {
TokenOut(Token.BUILTIN);
braceDepth--;
if (braceDepth != 0) {
gotoStart();
}
 else {
col=ncol;
inPcal=false;
cdepth=1;
pcalEnd=getNextTokenPosition();
pseudoCom=true;
state=COMMENT;
}
}
 else {
state=BUILT_IN;
}
}
 else {
state=BUILT_IN;
}
}
 else if (nextChar == '\t') {
if (mode == MODULE) {
Debug.ReportError("Input ended before end of module");
}
;
state=DONE;
}
 else {
addNextChar();
TokenizingError("Illegal lexeme");
}
;
break;
case ID:
if ((token.length() == 3) && (token.equals("WF_") || token.equals("SF_"))) {
TokenOut(Token.BUILTIN);
gotoStart();
}
 else if (Misc.IsLetter(nextChar) || Misc.IsDigit(nextChar)) {
addNextChar();
}
 else if (BuiltInSymbols.IsBuiltInSymbol(token,inPcal)) {
if (token.equals("MODULE")) {
mdepth=mdepth + 1;
TokenOut(Token.BUILTIN);
gotoStart();
}
 else if (inPcal && token.equals("algorithm") && !isCSyntax && (mode == MODULE)) {
TokenOut(Token.BUILTIN);
col=ncol;
pcalEnd=getNextTokenPosition();
cdepth=1;
inPcal=false;
pseudoCom=true;
state=COMMENT;
}
 else {
boolean cbl=inPcal && BuiltInSymbols.CanPrecedeLabel(token);
TokenOut(Token.BUILTIN);
canBeLabel=cbl;
gotoStart();
}
}
 else if (inPcal && canBeLabel) {
token1=token;
col1=col;
state=ID_OR_PCAL_LABEL;
}
 else {
TokenOut(Token.IDENT);
gotoStart();
}
;
break;
case ID_OR_PCAL_LABEL:
while (Misc.IsSpace(nextChar)) {
addNextChar();
}
if (nextChar == ':') {
addNextChar();
if ((nextChar != '=') && (nextChar != ':')) {
token1=token;
state=PCAL_LABEL;
}
 else {
reader.backspace();
ncol--;
nextChar=':';
token=token1;
TokenOut(Token.IDENT);
gotoStart();
}
}
 else {
token=token1;
TokenOut(Token.IDENT);
gotoStart();
}
break;
case PCAL_LABEL:
while (Misc.IsSpace(nextChar)) {
addNextChar();
}
if ((nextChar == '+') || (nextChar == '-')) {
addNextChar();
TokenOut(Token.PCAL_LABEL);
gotoStart();
}
 else {
token=token1;
TokenOut(Token.PCAL_LABEL);
gotoStart();
}
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
TokenOut(Token.NUMBER);
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
 else if (nextChar == '*') {
skipNextChar();
token="";
state=LINE_COMMENT;
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
 else if (Misc.IsLetter(nextChar)) {
addNextChar();
if (token.charAt(0) == '\\') {
TokenizingError("Illegal lexeme");
}
 else {
state=ID;
}
;
}
 else {
TokenOut(Token.NUMBER);
gotoStart();
}
break;
case BSBUILT_IN:
if (Misc.IsLetter(nextChar) && (nextChar != '_')) {
addNextChar();
state=BSBUILT_IN;
}
 else if (BuiltInSymbols.IsBuiltInSymbol(token)) {
TokenOut(Token.BUILTIN);
gotoStart();
}
 else {
TokenizingError("Illegal lexeme ");
}
;
break;
case BUILT_IN:
if (BuiltInSymbols.IsBuiltInPrefix(token + nextChar,inPcal)) {
addNextChar();
}
 else {
if (!BuiltInSymbols.IsBuiltInSymbol(token,inPcal)) {
reader.backspace();
while (!BuiltInSymbols.IsBuiltInSymbol(token,inPcal)) {
reader.backspace();
if (token.length() == 0) {
TokenizingError("Illegal lexeme");
}
;
token=token.substring(0,token.length() - 1);
}
;
nextChar=reader.getNextChar();
}
;
boolean saved=BuiltInSymbols.CanPrecedeLabel(token);
TokenOut(Token.BUILTIN);
canBeLabel=saved;
gotoStart();
}
break;
case DASH1:
if (nextChar == '-') {
addNextChar();
state=DASH2;
}
 else {
state=BUILT_IN;
}
break;
case DASH2:
if (nextChar == '-') {
addNextChar();
state=DASH3;
}
 else {
state=BUILT_IN;
}
break;
case DASH3:
if (nextChar == '-') {
addNextChar();
state=DASHES;
}
 else {
TokenizingError("Illegal lexeme");
}
break;
case DASHES:
if (nextChar == '-') {
addNextChar();
state=DASHES;
}
 else {
TokenOut(Token.DASHES);
gotoStart();
}
break;
case EQ1:
if (nextChar == '=') {
addNextChar();
state=EQ2;
}
 else {
state=BUILT_IN;
}
break;
case EQ2:
if (nextChar == '=') {
addNextChar();
state=EQ3;
}
 else {
state=BUILT_IN;
}
break;
case EQ3:
if (nextChar == '=') {
addNextChar();
state=EQS;
}
 else {
TokenizingError("Illegal lexeme");
}
break;
case EQS:
if (nextChar == '=') {
addNextChar();
state=EQS;
}
 else {
mdepth=mdepth - 1;
TokenOut(Token.END_MODULE);
if ((mdepth > 0) || (mode == TLA)) {
gotoStart();
}
 else if (mdepth == 0) {
state=EPILOG;
}
 else {
Debug.ReportError("Extra end-of-module lexeme on line " + (reader.getLineNumber() + 1));
}
}
break;
case LEFT_PAREN:
if (nextChar == '*') {
skipNextChar();
cdepth=1;
state=COMMENT;
}
 else {
token="(";
state=BUILT_IN;
}
break;
case STRING:
if (nextChar == '\\') {
addNextChar();
state=ESC_STRING;
}
 else if (nextChar == '"') {
skipNextChar();
TokenOut(Token.STRING);
gotoStart();
}
 else if (BuiltInSymbols.IsStringChar(nextChar)) {
addNextChar();
state=STRING;
}
 else {
addNextChar();
TokenizingError("Illegal character in string");
}
break;
case ESC_STRING:
if ((nextChar == '\"') || (nextChar == '\\') || (nextChar == 't') || (nextChar == 'n') || (nextChar == 'f') || (nextChar == 'r')) {
addNextChar();
state=STRING;
}
 else {
addNextChar();
TokenizingError("Illegal character following \\ in string");
}
break;
case LINE_COMMENT:
if (nextChar == '(') {
skipNextChar();
state=LINE_COM_PAREN;
}
 else if ((nextChar == '*') && (cdepth > 0)) {
skipNextChar();
state=LINE_COM_STAR;
}
 else if ((nextChar == '\n') || (nextChar == '\t')) {
CommentTokenOut(CommentToken.LINE);
cdepth=0;
gotoStart();
}
 else {
if (cdepth == 0) {
addNextChar();
}
 else {
skipNextChar();
}
;
state=LINE_COMMENT;
}
break;
case LINE_COM_PAREN:
if (nextChar == '*') {
skipNextChar();
cdepth=cdepth + 1;
state=LINE_COMMENT;
}
 else {
if (cdepth == 0) {
token=token + "(";
}
;
state=LINE_COMMENT;
}
break;
case LINE_COM_STAR:
if (nextChar == ')') {
skipNextChar();
cdepth=cdepth - 1;
Debug.Assert(cdepth >= 0,"case LINE_COM_STAR");
state=LINE_COMMENT;
}
 else {
if (cdepth == 0) {
token=token + "*";
}
;
state=LINE_COMMENT;
}
break;
case COMMENT:
if (nextChar == '*') {
skipNextChar();
state=COMMENT_STAR;
}
 else if (nextChar == '(') {
skipNextChar();
state=COMMENT_PAREN;
}
 else if (nextChar == '\n') {
CommentTokenOut(CommentToken.BEGIN_OVERRUN);
skipNextChar();
startNewLine();
state=OR_COMMENT;
}
 else if (nextChar == '\t') {
Debug.ReportError("Input ended in the middle of a comment");
}
 else if ((nextChar == '-') && (cdepth == 1) && (mode == MODULE) && !hasPcal) {
token1=token;
col1=col;
token="";
col=ncol;
ORCom=false;
addNextChar();
state=C_DASH;
}
 else {
if (cdepth == 1) {
addNextChar();
}
 else {
skipNextChar();
}
;
}
break;
case COMMENT_STAR:
if (nextChar == ')') {
skipNextChar();
cdepth=cdepth - 1;
if (cdepth == 0) {
CommentTokenOut(CommentToken.NORMAL);
gotoStart();
}
 else {
state=COMMENT;
}
}
 else {
if (cdepth == 1) {
token=token + "*";
}
;
state=COMMENT;
}
break;
case C_DASH:
if (nextChar == '-') {
addNextChar();
state=C_DASH_DASH;
}
 else {
token=token1 + token;
col=col1;
state=ORCom ? OR_COMMENT : COMMENT;
}
break;
case C_DASH_DASH:
while (Misc.IsLetter(nextChar)) {
addNextChar();
}
boolean isAlgorithm=token.equals("--algorithm");
if (isAlgorithm || token.equals("--fair")) {
if (!Misc.isBlank(token1)) {
pseudoCom=true;
token2=token;
col2=col;
token=token1;
col=col1;
CommentTokenOut(ORCom ? CommentToken.END_OVERRUN : CommentToken.NORMAL);
token=token2;
col=col2;
}
hasPcal=true;
pcalStart=getNextTokenPosition();
TokenOut(Token.BUILTIN);
SkipSpaceAndNewlines();
if (isAlgorithm) {
state=GET_ALG_NAME;
}
 else {
state=GET_ALG_TOKEN;
}
}
 else {
token=token1 + token;
col=col1;
state=COMMENT;
}
break;
case GET_ALG_TOKEN:
while (Misc.IsLetter(nextChar)) {
addNextChar();
}
if (token.equals("algorithm")) {
TokenOut(Token.BUILTIN);
SkipSpaceAndNewlines();
state=GET_ALG_NAME;
}
 else {
pcalEnd=getNextTokenPosition();
pseudoCom=true;
state=COMMENT;
}
break;
case GET_ALG_NAME:
while (Misc.IsLetter(nextChar) || Misc.IsDigit(nextChar)) {
addNextChar();
}
if (Misc.hasLetter(token)) {
TokenOut(Token.IDENT);
SkipSpaceAndNewlines();
isCSyntax=(nextChar == '{');
cdepth=0;
inPcal=true;
braceDepth=0;
gotoStart();
}
 else {
pcalEnd=getNextTokenPosition();
pseudoCom=true;
state=COMMENT;
}
break;
case COMMENT_PAREN:
if (nextChar == '*') {
skipNextChar();
cdepth=cdepth + 1;
state=COMMENT;
}
 else {
if (cdepth == 1) {
token=token + "(";
}
;
state=COMMENT;
}
break;
case OR_COMMENT:
if (nextChar == '*') {
skipNextChar();
state=OR_COMMENT_STAR;
}
 else if (nextChar == '(') {
skipNextChar();
state=OR_COMMENT_PAREN;
}
 else if (nextChar == '\n') {
CommentTokenOut(CommentToken.OVERRUN);
skipNextChar();
startNewLine();
state=OR_COMMENT;
}
 else if ((nextChar == '-') && (cdepth == 1) && (mode == MODULE) && !hasPcal) {
token1=token;
col1=col;
token="";
col=ncol;
ORCom=true;
addNextChar();
state=C_DASH;
}
 else if (nextChar == '\t') {
Debug.ReportError("Input ended in the middle of a multi-line comment");
}
 else {
if (cdepth == 1) {
addNextChar();
}
 else {
skipNextChar();
}
;
}
break;
case OR_COMMENT_STAR:
if (nextChar == ')') {
skipNextChar();
cdepth=cdepth - 1;
Debug.Assert(cdepth >= 0);
if (cdepth == 0) {
CommentTokenOut(CommentToken.END_OVERRUN);
gotoStart();
}
 else {
state=OR_COMMENT;
}
}
 else {
if (cdepth == 1) {
token=token + "*";
}
;
state=OR_COMMENT;
}
break;
case OR_COMMENT_PAREN:
if (nextChar == '*') {
skipNextChar();
cdepth=cdepth + 1;
state=OR_COMMENT;
}
 else {
if (cdepth == 1) {
token=token + "(";
}
;
state=OR_COMMENT;
}
break;
case PROLOG:
if (nextChar == '-') {
token1=token;
col1=col;
col=ncol;
token="-";
skipNextChar();
state=PROLOG_DASH;
}
 else if (nextChar == '\n') {
TokenOut(Token.PROLOG);
skipNextChar();
startNewLine();
}
 else if (nextChar == '\t') {
Debug.ReportError("Input ended before beginning of module");
}
 else {
addNextChar();
}
break;
case PROLOG_DASH:
Debug.Assert(token.length() <= 3);
if (nextChar == '-') {
addNextChar();
if (token.length() == 4) {
state=PROLOG_DASHES;
}
 else {
}
;
}
 else {
token=token1 + token;
col=col1;
state=PROLOG;
}
break;
case PROLOG_DASHES:
if (nextChar == '-') {
addNextChar();
}
 else {
token2=token;
col2=col;
token="";
col=ncol;
state=PROLOG_SPACES;
}
break;
case PROLOG_SPACES:
if (nextChar == ' ') {
addNextChar();
}
 else if (Misc.IsLetter(nextChar)) {
token3=token;
col3=ncol;
token="";
state=PROLOG_ID;
}
 else {
token=token1 + token2;
col=col1;
state=PROLOG;
}
break;
case PROLOG_ID:
if (Misc.IsLetter(nextChar)) {
addNextChar();
}
 else if (token.equals("MODULE")) {
token=token1;
col=col1;
TokenOut(Token.PROLOG);
token=token2;
col=col2;
TokenOut(Token.DASHES);
token="MODULE";
col=col3;
TokenOut(Token.BUILTIN);
token="";
mdepth=1;
gotoStart();
}
 else {
token=token1 + token2 + token3;
col=col1;
state=PROLOG;
}
break;
case EPILOG:
if (nextChar == '\n') {
TokenOut(Token.EPILOG);
skipNextChar();
startNewLine();
}
 else if (nextChar == '\t') {
TokenOut(Token.EPILOG);
state=DONE;
}
 else {
addNextChar();
}
break;
default :
Debug.ReportBug("Illegal state in TokenizeSpec.Tokenize");
}
;
}
;
if (hasPcal) {
if (pcalEnd == null) {
pcalEnd=new Position(Integer.MAX_VALUE,0);
}
}
return vspecToArray();
}
