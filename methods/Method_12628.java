/** 
 * ????????
 * @return
 */
Lexeme peekLast(){
  if (this.tail != null) {
    return this.tail.lexeme;
  }
  return null;
}
