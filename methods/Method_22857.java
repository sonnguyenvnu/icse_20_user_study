/** 
 * Adds a token to the token list.
 * @param length The length of the token
 * @param id     The id of the token
 */
protected void addToken(int length,byte id){
  if (id >= Token.INTERNAL_FIRST && id <= Token.INTERNAL_LAST) {
    throw new InternalError("Invalid id: " + id);
  }
  if (length == 0 && id != Token.END) {
    return;
  }
  if (firstToken == null) {
    firstToken=new Token(length,id);
    lastToken=firstToken;
  }
 else   if (lastToken == null) {
    lastToken=firstToken;
    firstToken.length=length;
    firstToken.id=id;
  }
 else   if (lastToken.next == null) {
    lastToken.next=new Token(length,id);
    lastToken=lastToken.next;
  }
 else {
    lastToken=lastToken.next;
    lastToken.length=length;
    lastToken.id=id;
  }
}
