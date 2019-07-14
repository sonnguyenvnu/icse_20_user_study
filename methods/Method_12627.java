/** 
 * ????????????
 * @return Lexeme
 */
Lexeme pollFirst(){
  if (this.size == 1) {
    Lexeme first=this.head.lexeme;
    this.head=null;
    this.tail=null;
    this.size--;
    return first;
  }
 else   if (this.size > 1) {
    Lexeme first=this.head.lexeme;
    this.head=this.head.next;
    this.size--;
    return first;
  }
 else {
    return null;
  }
}
