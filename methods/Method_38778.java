public final String yytext(int startIndex,final int endIndexOffset){
  startIndex+=zzStartRead;
  return new String(zzBuffer,startIndex,zzMarkedPos - endIndexOffset - startIndex);
}
