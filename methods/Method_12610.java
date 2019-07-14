/** 
 * ??????????
 * @param context
 */
private void outputNumLexeme(AnalyzeContext context){
  if (nStart > -1 && nEnd > -1) {
    Lexeme newLexeme=new Lexeme(context.getBufferOffset(),nStart,nEnd - nStart + 1,Lexeme.TYPE_CNUM);
    context.addLexeme(newLexeme);
  }
}
