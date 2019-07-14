/** 
 * ?CJK????????
 * @param index
 */
private void outputSingleCJK(int index){
  if (CharacterUtil.CHAR_CHINESE == this.charTypes[index]) {
    Lexeme singleCharLexeme=new Lexeme(this.buffOffset,index,1,Lexeme.TYPE_CNCHAR);
    this.results.add(singleCharLexeme);
  }
 else   if (CharacterUtil.CHAR_OTHER_CJK == this.charTypes[index]) {
    Lexeme singleCharLexeme=new Lexeme(this.buffOffset,index,1,Lexeme.TYPE_OTHER_CJK);
    this.results.add(singleCharLexeme);
  }
}
