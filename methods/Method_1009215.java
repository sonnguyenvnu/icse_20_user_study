/** 
 * Resumes scanning until the next regular expression is matched, the end of input is encountered or an I/O-Error occurs.
 * @return      the next token
 * @exception java.io.IOException  if any I/O-Error occurs
 */
public IElementType advance() throws java.io.IOException {
  int zzInput;
  int zzAction;
  int zzCurrentPosL;
  int zzMarkedPosL;
  int zzEndReadL=zzEndRead;
  CharSequence zzBufferL=zzBuffer;
  int[] zzTransL=ZZ_TRANS;
  int[] zzRowMapL=ZZ_ROWMAP;
  int[] zzAttrL=ZZ_ATTRIBUTE;
  while (true) {
    zzMarkedPosL=zzMarkedPos;
    zzAction=-1;
    zzCurrentPosL=zzCurrentPos=zzStartRead=zzMarkedPosL;
    zzState=ZZ_LEXSTATE[zzLexicalState];
    int zzAttributes=zzAttrL[zzState];
    if ((zzAttributes & 1) == 1) {
      zzAction=zzState;
    }
    zzForAction: {
      while (true) {
        if (zzCurrentPosL < zzEndReadL) {
          zzInput=Character.codePointAt(zzBufferL,zzCurrentPosL);
          zzCurrentPosL+=Character.charCount(zzInput);
        }
 else         if (zzAtEOF) {
          zzInput=YYEOF;
          break zzForAction;
        }
 else {
          zzCurrentPos=zzCurrentPosL;
          zzMarkedPos=zzMarkedPosL;
          boolean eof=zzRefill();
          zzCurrentPosL=zzCurrentPos;
          zzMarkedPosL=zzMarkedPos;
          zzBufferL=zzBuffer;
          zzEndReadL=zzEndRead;
          if (eof) {
            zzInput=YYEOF;
            break zzForAction;
          }
 else {
            zzInput=Character.codePointAt(zzBufferL,zzCurrentPosL);
            zzCurrentPosL+=Character.charCount(zzInput);
          }
        }
        int zzNext=zzTransL[zzRowMapL[zzState] + ZZ_CMAP(zzInput)];
        if (zzNext == -1)         break zzForAction;
        zzState=zzNext;
        zzAttributes=zzAttrL[zzState];
        if ((zzAttributes & 1) == 1) {
          zzAction=zzState;
          zzMarkedPosL=zzCurrentPosL;
          if ((zzAttributes & 8) == 8)           break zzForAction;
        }
      }
    }
    zzMarkedPos=zzMarkedPosL;
    if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
      zzAtEOF=true;
      zzDoEOF();
      return null;
    }
 else {
switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
case 1:
{
          return TokenType.BAD_CHARACTER;
        }
case 178:
      break;
case 2:
{
      pushAndBegin(YYINITIAL);
      pushAndBegin(MULTILINE_WHITE_SPACE_MAYBE);
      return ElixirTypes.OPENING_CURLY;
    }
case 179:
  break;
case 3:
{
  if (!stack.empty()) {
    popAndBegin();
  }
  return ElixirTypes.CLOSING_CURLY;
}
case 180:
break;
case 4:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.RELATIONAL_OPERATOR;
}
case 181:
break;
case 5:
{
pushAndBegin(CALL_MAYBE);
return ElixirTypes.IDENTIFIER_TOKEN;
}
case 182:
break;
case 6:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.CAPTURE_OPERATOR;
}
case 183:
break;
case 7:
{
pushAndBegin(SIGIL);
return ElixirTypes.TILDE;
}
case 184:
break;
case 8:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.PIPE_OPERATOR;
}
case 185:
break;
case 9:
{
pushAndBegin(KEYWORD_PAIR_MAYBE);
return ElixirTypes.UNARY_OPERATOR;
}
case 186:
break;
case 10:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.MATCH_OPERATOR;
}
case 187:
break;
case 11:
{
pushAndBegin(KEYWORD_PAIR_MAYBE);
return ElixirTypes.STRUCT_OPERATOR;
}
case 188:
break;
case 12:
{
pushAndBegin(DOT_OPERATION);
return ElixirTypes.DOT_OPERATOR;
}
case 189:
break;
case 13:
{
pushAndBegin(DUAL_OPERATION);
return ElixirTypes.DUAL_OPERATOR;
}
case 190:
break;
case 14:
{
pushAndBegin(ATOM_START);
return ElixirTypes.COLON;
}
case 191:
break;
case 15:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.AT_OPERATOR;
}
case 192:
break;
case 16:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.DIVISION_OPERATOR;
}
case 193:
break;
case 17:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.MULTIPLICATION_OPERATOR;
}
case 194:
break;
case 18:
{
pushAndBegin(CHAR_TOKENIZATION);
return ElixirTypes.CHAR_TOKENIZER;
}
case 195:
break;
case 19:
{
pushAndBegin(DECIMAL_WHOLE_NUMBER);
return ElixirTypes.VALID_DECIMAL_DIGITS;
}
case 196:
break;
case 20:
{
pushAndBegin(SIGN_OPERATION_MAYBE);
return ElixirTypes.COMMA;
}
case 197:
break;
case 21:
{
pushAndBegin(KEYWORD_PAIR_MAYBE);
return ElixirTypes.ALIAS_TOKEN;
}
case 198:
break;
case 22:
{
pushAndBegin(MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.SEMICOLON;
}
case 199:
break;
case 23:
{
pushAndBegin(SIGN_OPERATION_MAYBE);
return ElixirTypes.EOL;
}
case 200:
break;
case 24:
{
return TokenType.WHITE_SPACE;
}
case 201:
break;
case 25:
{
pushAndBegin(MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.OPENING_PARENTHESIS;
}
case 202:
break;
case 26:
{
return ElixirTypes.COMMENT;
}
case 203:
break;
case 27:
{
return ElixirTypes.CLOSING_BRACKET;
}
case 204:
break;
case 28:
{
pushAndBegin(MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.OPENING_BRACKET;
}
case 205:
break;
case 29:
{
return ElixirTypes.CLOSING_PARENTHESIS;
}
case 206:
break;
case 30:
{
pushAndBegin(KEYWORD_PAIR_MAYBE);
startQuote(yytext());
return promoterType();
}
case 207:
break;
case 31:
{
popAndBegin();
return ElixirTypes.ATOM_FRAGMENT;
}
case 208:
break;
case 32:
{
popAndBegin();
startQuote(yytext());
return promoterType();
}
case 209:
break;
case 33:
{
yybegin(UNKNOWN_BASE_WHOLE_NUMBER);
return ElixirTypes.UNKNOWN_WHOLE_NUMBER_BASE;
}
case 210:
break;
case 34:
{
yybegin(OCTAL_WHOLE_NUMBER);
return ElixirTypes.OCTAL_WHOLE_NUMBER_BASE;
}
case 211:
break;
case 35:
{
yybegin(BINARY_WHOLE_NUMBER);
return ElixirTypes.BINARY_WHOLE_NUMBER_BASE;
}
case 212:
break;
case 36:
{
yybegin(BINARY_WHOLE_NUMBER);
return ElixirTypes.OBSOLETE_BINARY_WHOLE_NUMBER_BASE;
}
case 213:
break;
case 37:
{
yybegin(HEXADECIMAL_WHOLE_NUMBER);
return ElixirTypes.HEXADECIMAL_WHOLE_NUMBER_BASE;
}
case 214:
break;
case 38:
{
yybegin(HEXADECIMAL_WHOLE_NUMBER);
return ElixirTypes.OBSOLETE_HEXADECIMAL_WHOLE_NUMBER_BASE;
}
case 215:
break;
case 39:
{
handleInLastState();
}
case 216:
break;
case 40:
{
return ElixirTypes.INVALID_BINARY_DIGITS;
}
case 217:
break;
case 41:
{
return ElixirTypes.VALID_BINARY_DIGITS;
}
case 218:
break;
case 42:
{
handleInLastState();
return ElixirTypes.CALL;
}
case 219:
break;
case 43:
{
handleInState(KEYWORD_PAIR_MAYBE);
}
case 220:
break;
case 44:
{
popAndBegin();
return ElixirTypes.CHAR_LIST_FRAGMENT;
}
case 221:
break;
case 45:
{
yybegin(ESCAPE_SEQUENCE);
return ElixirTypes.ESCAPE;
}
case 222:
break;
case 46:
{
return ElixirTypes.INVALID_DECIMAL_DIGITS;
}
case 223:
break;
case 47:
{
return ElixirTypes.VALID_DECIMAL_DIGITS;
}
case 224:
break;
case 48:
{
return ElixirTypes.DECIMAL_SEPARATOR;
}
case 225:
break;
case 49:
{
handleInState(DECIMAL_EXPONENT);
}
case 226:
break;
case 50:
{
yybegin(DECIMAL_EXPONENT);
return ElixirTypes.DUAL_OPERATOR;
}
case 227:
break;
case 51:
{
yybegin(DECIMAL_EXPONENT_SIGN);
return ElixirTypes.EXPONENT_MARK;
}
case 228:
break;
case 52:
{
yybegin(CALL_MAYBE);
return ElixirTypes.RELATIONAL_OPERATOR;
}
case 229:
break;
case 53:
{
yybegin(CALL_OR_KEYWORD_PAIR_MAYBE);
return ElixirTypes.IDENTIFIER_TOKEN;
}
case 230:
break;
case 54:
{
yybegin(CALL_MAYBE);
return ElixirTypes.CAPTURE_OPERATOR;
}
case 231:
break;
case 55:
{
yybegin(CALL_MAYBE);
return ElixirTypes.PIPE_OPERATOR;
}
case 232:
break;
case 56:
{
yybegin(CALL_MAYBE);
return ElixirTypes.UNARY_OPERATOR;
}
case 233:
break;
case 57:
{
yybegin(CALL_MAYBE);
return ElixirTypes.MATCH_OPERATOR;
}
case 234:
break;
case 58:
{
yybegin(CALL_MAYBE);
return ElixirTypes.STRUCT_OPERATOR;
}
case 235:
break;
case 59:
{
yybegin(CALL_MAYBE);
return ElixirTypes.DUAL_OPERATOR;
}
case 236:
break;
case 60:
{
yybegin(CALL_MAYBE);
return ElixirTypes.AT_OPERATOR;
}
case 237:
break;
case 61:
{
yybegin(CALL_MAYBE);
return ElixirTypes.DIVISION_OPERATOR;
}
case 238:
break;
case 62:
{
yybegin(CALL_MAYBE);
return ElixirTypes.MULTIPLICATION_OPERATOR;
}
case 239:
break;
case 63:
{
yybegin(CALL_MAYBE);
startQuote(yytext());
return promoterType();
}
case 240:
break;
case 64:
{
popAndBegin();
return ElixirTypes.SIGNIFICANT_WHITE_SPACE;
}
case 241:
break;
case 65:
{
yybegin(GROUP);
return fragmentType();
}
case 242:
break;
case 66:
{
yybegin(GROUP);
return ElixirTypes.EOL;
}
case 243:
break;
case 67:
{
popAndBegin();
return ElixirTypes.ESCAPED_CHARACTER_TOKEN;
}
case 244:
break;
case 68:
{
yybegin(UNICODE_ESCAPE_SEQUENCE);
return ElixirTypes.UNICODE_ESCAPE_CHARACTER;
}
case 245:
break;
case 69:
{
popAndBegin();
return ElixirTypes.EOL;
}
case 246:
break;
case 70:
{
yybegin(HEXADECIMAL_ESCAPE_SEQUENCE);
return ElixirTypes.HEXADECIMAL_WHOLE_NUMBER_BASE;
}
case 247:
break;
case 71:
{
popAndBegin();
return ElixirTypes.CLOSING_CURLY;
}
case 248:
break;
case 72:
{
return ElixirTypes.VALID_HEXADECIMAL_DIGITS;
}
case 249:
break;
case 73:
{
return fragmentType();
}
case 250:
break;
case 74:
{
if (isTerminator(yytext())) {
if (isSigil()) {
yybegin(SIGIL_MODIFIERS);
return terminatorType();
}
 else {
org.elixir_lang.lexer.StackFrame stackFrame=pop();
yybegin(stackFrame.getLastLexicalState());
return stackFrame.terminatorType();
}
}
 else {
return fragmentType();
}
}
case 251:
break;
case 75:
{
if (isInterpolating()) {
pushAndBegin(ESCAPE_SEQUENCE);
return ElixirTypes.ESCAPE;
}
 else {
yybegin(ESCAPE_IN_LITERAL_GROUP);
return fragmentType();
}
}
case 252:
break;
case 76:
{
if (isInterpolating()) {
pushAndBegin(ESCAPE_SEQUENCE);
return ElixirTypes.ESCAPE;
}
 else {
return fragmentType();
}
}
case 253:
break;
case 77:
{
yybegin(GROUP_HEREDOC_LINE_START);
return ElixirTypes.EOL;
}
case 254:
break;
case 78:
{
yypushback(yylength());
yybegin(GROUP_HEREDOC_LINE_BODY);
return ElixirTypes.EOL;
}
case 255:
break;
case 79:
{
handleInState(GROUP_HEREDOC_LINE_BODY);
}
case 256:
break;
case 80:
{
yybegin(GROUP_HEREDOC_LINE_BODY);
return ElixirTypes.HEREDOC_LINE_WHITE_SPACE_TOKEN;
}
case 257:
break;
case 81:
{
yybegin(GROUP_HEREDOC_LINE_START);
return ElixirTypes.EOL;
}
case 258:
break;
case 82:
{
yybegin(EXTENDED_HEXADECIMAL_ESCAPE_SEQUENCE);
return ElixirTypes.OPENING_CURLY;
}
case 259:
break;
case 83:
{
popAndBegin();
return ElixirTypes.VALID_HEXADECIMAL_DIGITS;
}
case 260:
break;
case 84:
{
return ElixirTypes.INVALID_HEXADECIMAL_DIGITS;
}
case 261:
break;
case 85:
{
popAndBegin();
return ElixirTypes.INTERPOLATION_END;
}
case 262:
break;
case 86:
{
popAndBegin();
return TokenType.WHITE_SPACE;
}
case 263:
break;
case 87:
{
popAndBegin();
return ElixirTypes.EOL;
}
case 264:
break;
case 88:
{
setPromoter(yytext());
yybegin(GROUP);
return promoterType();
}
case 265:
break;
case 89:
{
return ElixirTypes.INVALID_OCTAL_DIGITS;
}
case 266:
break;
case 90:
{
return ElixirTypes.VALID_OCTAL_DIGITS;
}
case 267:
break;
case 91:
{
handleInState(SIGN_OPERATION);
}
case 268:
break;
case 92:
{
yybegin(SIGN_OPERATION_KEYWORD_PAIR_MAYBE);
return ElixirTypes.SIGN_OPERATOR;
}
case 269:
break;
case 93:
{
popAndBegin();
return ElixirTypes.DIVISION_OPERATOR;
}
case 270:
break;
case 94:
{
return ElixirTypes.EOL;
}
case 271:
break;
case 95:
{
nameSigil(yytext());
yybegin(NAMED_SIGIL);
return sigilNameType();
}
case 272:
break;
case 96:
{
return ElixirTypes.SIGIL_MODIFIER;
}
case 273:
break;
case 97:
{
return ElixirTypes.INVALID_UNKNOWN_BASE_DIGITS;
}
case 274:
break;
case 98:
{
pushAndBegin(MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.OPENING_BIT;
}
case 275:
break;
case 99:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.TWO_OPERATOR;
}
case 276:
break;
case 100:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.ARROW_OPERATOR;
}
case 277:
break;
case 101:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.IN_MATCH_OPERATOR;
}
case 278:
break;
case 102:
{
int zzFState=39;
int zzFPos=zzStartRead;
if (zzFin.length <= zzBufferL.length()) {
zzFin=new boolean[zzBufferL.length() + 1];
}
boolean zzFinL[]=zzFin;
while (zzFState != -1 && zzFPos < zzMarkedPos) {
zzFinL[zzFPos]=((zzAttrL[zzFState] & 1) == 1);
zzInput=Character.codePointAt(zzBufferL,zzFPos);
zzFPos+=Character.charCount(zzInput);
zzFState=zzTransL[zzRowMapL[zzFState] + ZZ_CMAP(zzInput)];
}
if (zzFState != -1) {
zzFinL[zzFPos++]=((zzAttrL[zzFState] & 1) == 1);
}
while (zzFPos <= zzMarkedPos) {
zzFinL[zzFPos++]=false;
}
zzFState=40;
zzFPos=zzMarkedPos;
while (!zzFinL[zzFPos] || (zzAttrL[zzFState] & 1) != 1) {
zzInput=Character.codePointBefore(zzBufferL,zzFPos);
zzFPos-=Character.charCount(zzInput);
zzFState=zzTransL[zzRowMapL[zzFState] + ZZ_CMAP(zzInput)];
}
;
zzMarkedPos=zzFPos;
}
{
pushAndBegin(REFERENCE_OPERATION);
return ElixirTypes.IDENTIFIER_TOKEN;
}
case 279:
break;
case 103:
{
return ElixirTypes.CLOSING_BIT;
}
case 280:
break;
case 104:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.AND_SYMBOL_OPERATOR;
}
case 281:
break;
case 105:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.DO;
}
case 282:
break;
case 106:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.OR_SYMBOL_OPERATOR;
}
case 283:
break;
case 107:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.COMPARISON_OPERATOR;
}
case 284:
break;
case 108:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.ASSOCIATION_OPERATOR;
}
case 285:
break;
case 109:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.OR_WORD_OPERATOR;
}
case 286:
break;
case 110:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.RANGE_OPERATOR;
}
case 287:
break;
case 111:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.STAB_OPERATOR;
}
case 288:
break;
case 112:
{
pushAndBegin(MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.TYPE_OPERATOR;
}
case 289:
break;
case 113:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzStartRead,1);
{
return ElixirTypes.COLON;
}
case 290:
break;
case 114:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.IN_OPERATOR;
}
case 291:
break;
case 115:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.FN;
}
case 292:
break;
case 116:
{
int zzFState=41;
int zzFPos=zzStartRead;
if (zzFin.length <= zzBufferL.length()) {
zzFin=new boolean[zzBufferL.length() + 1];
}
boolean zzFinL[]=zzFin;
while (zzFState != -1 && zzFPos < zzMarkedPos) {
zzFinL[zzFPos]=((zzAttrL[zzFState] & 1) == 1);
zzInput=Character.codePointAt(zzBufferL,zzFPos);
zzFPos+=Character.charCount(zzInput);
zzFState=zzTransL[zzRowMapL[zzFState] + ZZ_CMAP(zzInput)];
}
if (zzFState != -1) {
zzFinL[zzFPos++]=((zzAttrL[zzFState] & 1) == 1);
}
while (zzFPos <= zzMarkedPos) {
zzFinL[zzFPos++]=false;
}
zzFState=42;
zzFPos=zzMarkedPos;
while (!zzFinL[zzFPos] || (zzAttrL[zzFState] & 1) != 1) {
zzInput=Character.codePointBefore(zzBufferL,zzFPos);
zzFPos-=Character.charCount(zzInput);
zzFState=zzTransL[zzRowMapL[zzFState] + ZZ_CMAP(zzInput)];
}
;
zzMarkedPos=zzFPos;
}
{
return TokenType.WHITE_SPACE;
}
case 293:
break;
case 117:
{
int zzFState=43;
int zzFPos=zzStartRead;
if (zzFin.length <= zzBufferL.length()) {
zzFin=new boolean[zzBufferL.length() + 1];
}
boolean zzFinL[]=zzFin;
while (zzFState != -1 && zzFPos < zzMarkedPos) {
zzFinL[zzFPos]=((zzAttrL[zzFState] & 1) == 1);
zzInput=Character.codePointAt(zzBufferL,zzFPos);
zzFPos+=Character.charCount(zzInput);
zzFState=zzTransL[zzRowMapL[zzFState] + ZZ_CMAP(zzInput)];
}
if (zzFState != -1) {
zzFinL[zzFPos++]=((zzAttrL[zzFState] & 1) == 1);
}
while (zzFPos <= zzMarkedPos) {
zzFinL[zzFPos++]=false;
}
zzFState=45;
zzFPos=zzMarkedPos;
while (!zzFinL[zzFPos] || (zzAttrL[zzFState] & 1) != 1) {
zzInput=Character.codePointBefore(zzBufferL,zzFPos);
zzFPos-=Character.charCount(zzInput);
zzFState=zzTransL[zzRowMapL[zzFState] + ZZ_CMAP(zzInput)];
}
;
zzMarkedPos=zzFPos;
}
{
handleLastEOL();
return TokenType.WHITE_SPACE;
}
case 294:
break;
case 118:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzStartRead,1);
{
pushAndBegin(BASE_WHOLE_NUMBER_BASE);
return ElixirTypes.BASE_WHOLE_NUMBER_PREFIX;
}
case 295:
break;
case 119:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzStartRead,1);
{
yybegin(DECIMAL_FRACTION);
return ElixirTypes.DECIMAL_MARK;
}
case 296:
break;
case 120:
{
yybegin(CALL_MAYBE);
return ElixirTypes.TWO_OPERATOR;
}
case 297:
break;
case 121:
{
yybegin(CALL_MAYBE);
return ElixirTypes.ARROW_OPERATOR;
}
case 298:
break;
case 122:
{
yybegin(CALL_MAYBE);
return ElixirTypes.IN_MATCH_OPERATOR;
}
case 299:
break;
case 123:
{
yybegin(CALL_MAYBE);
return ElixirTypes.AND_SYMBOL_OPERATOR;
}
case 300:
break;
case 124:
{
yybegin(CALL_MAYBE);
return ElixirTypes.DO;
}
case 301:
break;
case 125:
{
yybegin(CALL_MAYBE);
return ElixirTypes.OR_SYMBOL_OPERATOR;
}
case 302:
break;
case 126:
{
yybegin(CALL_MAYBE);
return ElixirTypes.COMPARISON_OPERATOR;
}
case 303:
break;
case 127:
{
yybegin(CALL_MAYBE);
return ElixirTypes.OR_WORD_OPERATOR;
}
case 304:
break;
case 128:
{
yybegin(CALL_MAYBE);
return ElixirTypes.RANGE_OPERATOR;
}
case 305:
break;
case 129:
{
yybegin(CALL_MAYBE);
if (getLevel().compareTo(Level.V_1_6) < 0) {
return ElixirTypes.STAB_OPERATOR;
}
 else {
yypushback(1);
return ElixirTypes.DUAL_OPERATOR;
}
}
case 306:
break;
case 130:
{
yybegin(CALL_MAYBE);
return ElixirTypes.IN_OPERATOR;
}
case 307:
break;
case 131:
{
CharSequence groupTerminator=yytext().subSequence(1,yytext().length());
yypushback(groupTerminator.length());
if (isTerminator(groupTerminator) || isInterpolating()) {
pushAndBegin(ESCAPE_SEQUENCE);
return ElixirTypes.ESCAPE;
}
 else {
yybegin(ESCAPE_IN_LITERAL_GROUP);
return fragmentType();
}
}
case 308:
break;
case 132:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzStartRead,1);
{
if (isInterpolating()) {
pushAndBegin(ESCAPE_SEQUENCE);
}
 else {
yybegin(ESCAPE_IN_LITERAL_GROUP);
}
return ElixirTypes.ESCAPE;
}
case 309:
break;
case 133:
{
if (isInterpolating()) {
pushAndBegin(INTERPOLATION);
return ElixirTypes.INTERPOLATION_START;
}
 else {
return fragmentType();
}
}
case 310:
break;
case 134:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzStartRead,1);
{
if (isInterpolating()) {
pushAndBegin(ESCAPE_SEQUENCE);
return ElixirTypes.ESCAPE;
}
 else {
yybegin(GROUP_HEREDOC_LINE_ESCAPED_EOL);
return ElixirTypes.ESCAPE;
}
}
case 311:
break;
case 135:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzStartRead,1);
{
yybegin(MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.KEYWORD_PAIR_COLON;
}
case 312:
break;
case 136:
{
int zzFState=41;
int zzFPos=zzStartRead;
if (zzFin.length <= zzBufferL.length()) {
zzFin=new boolean[zzBufferL.length() + 1];
}
boolean zzFinL[]=zzFin;
while (zzFState != -1 && zzFPos < zzMarkedPos) {
zzFinL[zzFPos]=((zzAttrL[zzFState] & 1) == 1);
zzInput=Character.codePointAt(zzBufferL,zzFPos);
zzFPos+=Character.charCount(zzInput);
zzFState=zzTransL[zzRowMapL[zzFState] + ZZ_CMAP(zzInput)];
}
if (zzFState != -1) {
zzFinL[zzFPos++]=((zzAttrL[zzFState] & 1) == 1);
}
while (zzFPos <= zzMarkedPos) {
zzFinL[zzFPos++]=false;
}
zzFState=46;
zzFPos=zzMarkedPos;
while (!zzFinL[zzFPos] || (zzAttrL[zzFState] & 1) != 1) {
zzInput=Character.codePointBefore(zzBufferL,zzFPos);
zzFPos-=Character.charCount(zzInput);
zzFState=zzTransL[zzRowMapL[zzFState] + ZZ_CMAP(zzInput)];
}
;
zzMarkedPos=zzFPos;
}
{
return TokenType.WHITE_SPACE;
}
case 313:
break;
case 137:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzStartRead,1);
{
popAndBegin();
return ElixirTypes.KEYWORD_PAIR_COLON;
}
case 314:
break;
case 138:
{
int zzFState=39;
int zzFPos=zzStartRead;
if (zzFin.length <= zzBufferL.length()) {
zzFin=new boolean[zzBufferL.length() + 1];
}
boolean zzFinL[]=zzFin;
while (zzFState != -1 && zzFPos < zzMarkedPos) {
zzFinL[zzFPos]=((zzAttrL[zzFState] & 1) == 1);
zzInput=Character.codePointAt(zzBufferL,zzFPos);
zzFPos+=Character.charCount(zzInput);
zzFState=zzTransL[zzRowMapL[zzFState] + ZZ_CMAP(zzInput)];
}
if (zzFState != -1) {
zzFinL[zzFPos++]=((zzAttrL[zzFState] & 1) == 1);
}
while (zzFPos <= zzMarkedPos) {
zzFinL[zzFPos++]=false;
}
zzFState=40;
zzFPos=zzMarkedPos;
while (!zzFinL[zzFPos] || (zzAttrL[zzFState] & 1) != 1) {
zzInput=Character.codePointBefore(zzBufferL,zzFPos);
zzFPos-=Character.charCount(zzInput);
zzFState=zzTransL[zzRowMapL[zzFState] + ZZ_CMAP(zzInput)];
}
;
zzMarkedPos=zzFPos;
}
{
handleInLastState();
}
case 315:
break;
case 139:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzMarkedPos,-2);
{
pushAndBegin(KEYWORD_PAIR_MAYBE);
return ElixirTypes.ATOM_FRAGMENT;
}
case 316:
break;
case 140:
{
pushAndBegin(KEYWORD_PAIR_MAYBE);
return ElixirTypes.END;
}
case 317:
break;
case 141:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.NOT_OPERATOR;
}
case 318:
break;
case 142:
{
pushAndBegin(KEYWORD_PAIR_MAYBE);
return ElixirTypes.NIL;
}
case 319:
break;
case 143:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.AND_WORD_OPERATOR;
}
case 320:
break;
case 144:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.THREE_OPERATOR;
}
case 321:
break;
case 145:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzStartRead,1);
{
pushAndBegin(ATOM_START);
return ElixirTypes.COLON;
}
case 322:
break;
case 146:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzMarkedPos,-2);
{
handleLastEOL();
return TokenType.WHITE_SPACE;
}
case 323:
break;
case 147:
{
int zzFState=43;
int zzFPos=zzStartRead;
if (zzFin.length <= zzBufferL.length()) {
zzFin=new boolean[zzBufferL.length() + 1];
}
boolean zzFinL[]=zzFin;
while (zzFState != -1 && zzFPos < zzMarkedPos) {
zzFinL[zzFPos]=((zzAttrL[zzFState] & 1) == 1);
zzInput=Character.codePointAt(zzBufferL,zzFPos);
zzFPos+=Character.charCount(zzInput);
zzFState=zzTransL[zzRowMapL[zzFState] + ZZ_CMAP(zzInput)];
}
if (zzFState != -1) {
zzFinL[zzFPos++]=((zzAttrL[zzFState] & 1) == 1);
}
while (zzFPos <= zzMarkedPos) {
zzFinL[zzFPos++]=false;
}
zzFState=44;
zzFPos=zzMarkedPos;
while (!zzFinL[zzFPos] || (zzAttrL[zzFState] & 1) != 1) {
zzInput=Character.codePointBefore(zzBufferL,zzFPos);
zzFPos-=Character.charCount(zzInput);
zzFState=zzTransL[zzRowMapL[zzFState] + ZZ_CMAP(zzInput)];
}
;
zzMarkedPos=zzFPos;
}
{
handleLastEOL();
return TokenType.WHITE_SPACE;
}
case 324:
break;
case 148:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzMarkedPos,-2);
{
return ElixirTypes.SIGNIFICANT_WHITE_SPACE;
}
case 325:
break;
case 149:
{
startQuote(yytext());
return promoterType();
}
case 326:
break;
case 150:
{
yybegin(CALL_MAYBE);
return ElixirTypes.END;
}
case 327:
break;
case 151:
{
yybegin(CALL_MAYBE);
return ElixirTypes.NIL;
}
case 328:
break;
case 152:
{
yybegin(CALL_MAYBE);
return ElixirTypes.AND_WORD_OPERATOR;
}
case 329:
break;
case 153:
{
yybegin(CALL_MAYBE);
return ElixirTypes.THREE_OPERATOR;
}
case 330:
break;
case 154:
{
handleInLastState();
}
case 331:
break;
case 155:
{
if (isTerminator(yytext())) {
if (isSigil()) {
yybegin(SIGIL_MODIFIERS);
return terminatorType();
}
 else {
org.elixir_lang.lexer.StackFrame stackFrame=pop();
yybegin(stackFrame.getLastLexicalState());
return stackFrame.terminatorType();
}
}
 else {
handleInState(GROUP_HEREDOC_LINE_BODY);
}
}
case 332:
break;
case 156:
{
handleInState(GROUP_HEREDOC_END);
}
case 333:
break;
case 157:
{
if (isTerminator(yytext())) {
if (isSigil()) {
yybegin(SIGIL_MODIFIERS);
return terminatorType();
}
 else {
org.elixir_lang.lexer.StackFrame stackFrame=pop();
yybegin(stackFrame.getLastLexicalState());
return stackFrame.terminatorType();
}
}
 else {
return TokenType.BAD_CHARACTER;
}
}
case 334:
break;
case 158:
{
setPromoter(yytext());
yybegin(GROUP_HEREDOC_START);
return promoterType();
}
case 335:
break;
case 159:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzStartRead,2);
{
pushAndBegin(KEYWORD_PAIR_MAYBE);
return ElixirTypes.TUPLE_OPERATOR;
}
case 336:
break;
case 160:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.WHEN_OPERATOR;
}
case 337:
break;
case 161:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.ELSE;
}
case 338:
break;
case 162:
{
pushAndBegin(KEYWORD_PAIR_MAYBE);
return ElixirTypes.TRUE;
}
case 339:
break;
case 163:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzMarkedPos,-3);
{
handleLastEOL();
return TokenType.WHITE_SPACE;
}
case 340:
break;
case 164:
{
yybegin(CALL_MAYBE);
return ElixirTypes.WHEN_OPERATOR;
}
case 341:
break;
case 165:
{
yybegin(CALL_MAYBE);
return ElixirTypes.ELSE;
}
case 342:
break;
case 166:
{
yybegin(CALL_MAYBE);
return ElixirTypes.TRUE;
}
case 343:
break;
case 167:
{
String groupHeredocTerminator=yytext().toString().trim();
yypushback(3);
if (isTerminator(groupHeredocTerminator)) {
yybegin(GROUP_HEREDOC_END);
return ElixirTypes.HEREDOC_PREFIX_WHITE_SPACE;
}
 else {
yybegin(GROUP_HEREDOC_LINE_BODY);
return ElixirTypes.HEREDOC_LINE_WHITE_SPACE_TOKEN;
}
}
case 344:
break;
case 168:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.AFTER;
}
case 345:
break;
case 169:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzStartRead,3);
{
pushAndBegin(KEYWORD_PAIR_MAYBE);
return ElixirTypes.MAP_OPERATOR;
}
case 346:
break;
case 170:
{
pushAndBegin(KEYWORD_PAIR_MAYBE);
return ElixirTypes.FALSE;
}
case 347:
break;
case 171:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.CATCH;
}
case 348:
break;
case 172:
{
yybegin(CALL_MAYBE);
return ElixirTypes.AFTER;
}
case 349:
break;
case 173:
{
yybegin(CALL_MAYBE);
return ElixirTypes.FALSE;
}
case 350:
break;
case 174:
{
yybegin(CALL_MAYBE);
return ElixirTypes.CATCH;
}
case 351:
break;
case 175:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzStartRead,4);
{
pushAndBegin(KEYWORD_PAIR_MAYBE);
return ElixirTypes.BIT_STRING_OPERATOR;
}
case 352:
break;
case 176:
{
pushAndBegin(KEYWORD_PAIR_OR_MULTILINE_WHITE_SPACE_MAYBE);
return ElixirTypes.RESCUE;
}
case 353:
break;
case 177:
{
yybegin(CALL_MAYBE);
return ElixirTypes.RESCUE;
}
case 354:
break;
default :
zzScanError(ZZ_NO_MATCH);
}
}
}
}
