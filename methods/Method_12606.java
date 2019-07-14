/** 
 * ????
 */
private void compound(Lexeme result){
  if (!this.cfg.isUseSmart()) {
    return;
  }
  if (!this.results.isEmpty()) {
    if (Lexeme.TYPE_ARABIC == result.getLexemeType()) {
      Lexeme nextLexeme=this.results.peekFirst();
      boolean appendOk=false;
      if (Lexeme.TYPE_CNUM == nextLexeme.getLexemeType()) {
        appendOk=result.append(nextLexeme,Lexeme.TYPE_CNUM);
      }
 else       if (Lexeme.TYPE_COUNT == nextLexeme.getLexemeType()) {
        appendOk=result.append(nextLexeme,Lexeme.TYPE_CQUAN);
      }
      if (appendOk) {
        this.results.pollFirst();
      }
    }
    if (Lexeme.TYPE_CNUM == result.getLexemeType() && !this.results.isEmpty()) {
      Lexeme nextLexeme=this.results.peekFirst();
      boolean appendOk=false;
      if (Lexeme.TYPE_COUNT == nextLexeme.getLexemeType()) {
        appendOk=result.append(nextLexeme,Lexeme.TYPE_CQUAN);
      }
      if (appendOk) {
        this.results.pollFirst();
      }
    }
  }
}
