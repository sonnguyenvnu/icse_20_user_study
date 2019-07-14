/** 
 * ?????????????
 * @return Lexeme
 */
Lexeme pollLast(){
  if (this.size == 1) {
    Lexeme last=this.head.lexeme;
    this.head=null;
    this.tail=null;
    this.size--;
    return last;
  }
 else   if (this.size > 1) {
    Lexeme last=this.tail.lexeme;
    this.tail=this.tail.prev;
    this.size--;
    return last;
  }
 else {
    return null;
  }
}
