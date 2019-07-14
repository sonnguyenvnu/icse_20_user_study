/** 
 * Indicate that all tokens of type tokenType should be copied.  The copy is put in the stream of hidden tokens, and the original is returned in the stream of normal tokens.
 * @param tokenType   integer representing the token type to copied
 */
public void copy(int tokenType){
  copyMask.add(tokenType);
}
