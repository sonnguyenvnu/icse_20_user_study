/** 
 * ??????????
 * @return
 */
private boolean needCountScan(AnalyzeContext context){
  if ((nStart != -1 && nEnd != -1) || !countHits.isEmpty()) {
    return true;
  }
 else {
    if (!context.getOrgLexemes().isEmpty()) {
      Lexeme l=context.getOrgLexemes().peekLast();
      if ((Lexeme.TYPE_CNUM == l.getLexemeType() || Lexeme.TYPE_ARABIC == l.getLexemeType()) && (l.getBegin() + l.getLength() == context.getCursor())) {
        return true;
      }
    }
  }
  return false;
}
