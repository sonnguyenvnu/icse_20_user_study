/** 
 * Access the currently edited document.
 * @return the document object
 */
public Document currentDocument(){
  return getCurrentTab().getDocument();
}
