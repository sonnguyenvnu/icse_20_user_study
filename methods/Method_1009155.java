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
          return Types.DATA;
        }
case 14:
      break;
case 2:
{
      handleInState(YYINITIAL);
    }
case 15:
  break;
case 3:
{
  return Types.COMMENT;
}
case 16:
break;
case 4:
{
return Types.ELIXIR;
}
case 17:
break;
case 5:
{
handleInState(ELIXIR);
return Types.EMPTY_MARKER;
}
case 18:
break;
case 6:
{
yybegin(COMMENT);
return Types.COMMENT_MARKER;
}
case 19:
break;
case 7:
{
yybegin(ELIXIR);
return Types.EQUALS_MARKER;
}
case 20:
break;
case 8:
{
yybegin(ELIXIR);
return Types.FORWARD_SLASH_MARKER;
}
case 21:
break;
case 9:
{
yybegin(ELIXIR);
return Types.PIPE_MARKER;
}
case 22:
break;
case 10:
{
yybegin(MARKER_MAYBE);
return Types.OPENING;
}
case 23:
break;
case 11:
{
yybegin(WHITESPACE_MAYBE);
return Types.CLOSING;
}
case 24:
break;
case 12:
{
return Types.ESCAPED_OPENING;
}
case 25:
break;
case 13:
zzMarkedPos=Character.offsetByCodePoints(zzBufferL,zzMarkedPos,-3);
{
yybegin(YYINITIAL);
return TokenType.WHITE_SPACE;
}
case 26:
break;
default :
zzScanError(ZZ_NO_MATCH);
}
}
}
}
