/** 
 * ?????????
 * @param context
 * @return
 */
private boolean processArabicLetter(AnalyzeContext context){
  boolean needLock=false;
  if (this.arabicStart == -1) {
    if (CharacterUtil.CHAR_ARABIC == context.getCurrentCharType()) {
      this.arabicStart=context.getCursor();
      this.arabicEnd=this.arabicStart;
    }
  }
 else {
    if (CharacterUtil.CHAR_ARABIC == context.getCurrentCharType()) {
      this.arabicEnd=context.getCursor();
    }
 else     if (CharacterUtil.CHAR_USELESS == context.getCurrentCharType() && this.isNumConnector(context.getCurrentChar())) {
    }
 else {
      Lexeme newLexeme=new Lexeme(context.getBufferOffset(),this.arabicStart,this.arabicEnd - this.arabicStart + 1,Lexeme.TYPE_ARABIC);
      context.addLexeme(newLexeme);
      this.arabicStart=-1;
      this.arabicEnd=-1;
    }
  }
  if (context.isBufferConsumed() && (this.arabicStart != -1 && this.arabicEnd != -1)) {
    Lexeme newLexeme=new Lexeme(context.getBufferOffset(),this.arabicStart,this.arabicEnd - this.arabicStart + 1,Lexeme.TYPE_ARABIC);
    context.addLexeme(newLexeme);
    this.arabicStart=-1;
    this.arabicEnd=-1;
  }
  if (this.arabicStart == -1 && this.arabicEnd == -1) {
    needLock=false;
  }
 else {
    needLock=true;
  }
  return needLock;
}
