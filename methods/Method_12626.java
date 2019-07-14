/** 
 * ????????
 * @return
 */
Lexeme peekFirst(){
  if (this.head != null) {
    return this.head.lexeme;
  }
  return null;
}
