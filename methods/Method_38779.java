/** 
 * Resumes scanning until the next regular expression is matched, the end of input is encountered or an I/O-Error occurs.
 * @return      the next token
 * @exception java.io.IOException  if any I/O-Error occurs
 */
public int yylex() throws java.io.IOException {
  int zzInput;
  int zzAction;
  int zzCurrentPosL;
  int zzMarkedPosL;
  int zzEndReadL=zzEndRead;
  char[] zzBufferL=zzBuffer;
  char[] zzCMapL=ZZ_CMAP;
  int[] zzTransL=ZZ_TRANS;
  int[] zzRowMapL=ZZ_ROWMAP;
  int[] zzAttrL=ZZ_ATTRIBUTE;
  while (true) {
    zzMarkedPosL=zzMarkedPos;
    yychar+=zzMarkedPosL - zzStartRead;
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
          zzInput=Character.codePointAt(zzBufferL,zzCurrentPosL,zzEndReadL);
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
            zzInput=Character.codePointAt(zzBufferL,zzCurrentPosL,zzEndReadL);
            zzCurrentPosL+=Character.charCount(zzInput);
          }
        }
        int zzNext=zzTransL[zzRowMapL[zzState] + zzCMapL[zzInput]];
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
{
        return 0;
      }
    }
 else {
switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
case 1:
{
          cssSelector.setCombinator(Combinator.DESCENDANT);
          stateReset();
        }
case 20:
      break;
case 2:
{
      cssSelector=new CssSelector(yytext());
      selectors.add(cssSelector);
      stateSelector();
    }
case 21:
  break;
case 3:
{
  cssSelector=new CssSelector();
  selectors.add(cssSelector);
  yypushback(1);
  stateSelector();
}
case 22:
break;
case 4:
{
}
case 23:
break;
case 5:
{
cssSelector=new CssSelector();
selectors.add(cssSelector);
stateSelector();
}
case 24:
break;
case 6:
{
throw new CSSellyException("Illegal character <" + yytext() + ">.",yystate(),line(),column());
}
case 25:
break;
case 7:
{
yypushback(1);
stateCombinator();
}
case 26:
break;
case 8:
{
stateAttr();
}
case 27:
break;
case 9:
{
cssSelector.addAttributeSelector(yytext());
}
case 28:
break;
case 10:
{
stateSelector();
}
case 29:
break;
case 11:
{
throw new CSSellyException("Invalid combinator <" + yytext() + ">.",yystate(),line(),column());
}
case 30:
break;
case 12:
{
cssSelector.setCombinator(Combinator.GENERAL_SIBLING);
stateReset();
}
case 31:
break;
case 13:
{
cssSelector.setCombinator(Combinator.CHILD);
stateReset();
}
case 32:
break;
case 14:
{
cssSelector.setCombinator(Combinator.ADJACENT_SIBLING);
stateReset();
}
case 33:
break;
case 15:
{
cssSelector.addPseudoFunctionSelector(pseudoFnName,yytext(0,1));
stateSelector();
}
case 34:
break;
case 16:
{
cssSelector.addClassSelector(yytext(1));
}
case 35:
break;
case 17:
{
cssSelector.addIdSelector(yytext(1));
}
case 36:
break;
case 18:
{
cssSelector.addPseudoClassSelector(yytext(yycharat(1) == ':' ? 2 : 1));
}
case 37:
break;
case 19:
{
pseudoFnName=yytext(yycharat(1) == ':' ? 2 : 1,1);
statePseudoFn();
}
case 38:
break;
default :
zzScanError(ZZ_NO_MATCH);
}
}
}
}
