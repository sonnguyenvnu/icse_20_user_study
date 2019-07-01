/** 
 * Gets the token previous to the last returned token.
 * @return the previous token
 */
@Override public String _XXXXX_(){
  if (hasPrevious()) {
    return tokens[--tokenPos];
  }
  throw new NoSuchElementException();
}