/** 
 * ????????????????
 * @param lexeme
 * @return
 */
boolean checkCross(Lexeme lexeme){
  return (lexeme.getBegin() >= this.pathBegin && lexeme.getBegin() < this.pathEnd) || (this.pathBegin >= lexeme.getBegin() && this.pathBegin < lexeme.getBegin() + lexeme.getLength());
}
