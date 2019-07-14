/** 
 * Sets the token marker that is to be used to split lines of this document up into tokens. May throw an exception if this is not supported for this type of document.
 * @param tm The new token marker
 */
public void setTokenMarker(TokenMarker tm){
  if (tm == null) {
    tokenMarker=null;
    return;
  }
  tokenMarker=tm.createStateInstance();
  tokenMarker.insertLines(0,getDefaultRootElement().getElementCount());
  tokenizeLines();
}
