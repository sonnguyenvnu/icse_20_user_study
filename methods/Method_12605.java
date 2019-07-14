/** 
 * ??lexeme  ??????
 * @return
 */
Lexeme getNextLexeme(){
  Lexeme result=this.results.pollFirst();
  while (result != null) {
    this.compound(result);
    if (Dictionary.getSingleton().isStopWord(this.segmentBuff,result.getBegin(),result.getLength())) {
      result=this.results.pollFirst();
    }
 else {
      result.setLexemeText(String.valueOf(segmentBuff,result.getBegin(),result.getLength()));
      break;
    }
  }
  return result;
}
