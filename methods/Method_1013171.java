/** 
 * Clears the view
 */
public void clear(){
  errorViewer.setDocument(EMPTY_DOCUMENT());
  setTraceInput(new TLCError());
  traceExplorerComposite.getTableViewer().setInput(new Vector<Formula>());
  valueViewer.setInput(EMPTY_DOCUMENT());
}
