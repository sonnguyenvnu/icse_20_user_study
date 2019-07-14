/** 
 * ?????????
 * @param l
 * @param lexemeType
 * @return boolean ????????
 */
public boolean append(Lexeme l,int lexemeType){
  if (l != null && this.getEndPosition() == l.getBeginPosition()) {
    this.length+=l.getLength();
    this.lexemeType=lexemeType;
    return true;
  }
 else {
    return false;
  }
}
