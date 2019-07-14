/** 
 * Reparses the document, by passing all lines to the token marker. This should be called after the document is first loaded.
 */
public void tokenizeLines(){
  tokenizeLines(0,getDefaultRootElement().getElementCount());
}
