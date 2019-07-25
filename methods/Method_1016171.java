/** 
 * Splits the specified document into a separate pane.
 * @param document  document to split
 * @param direction split direction
 */
public void split(final T document,final int direction){
  final WebDocumentPane pane=getDocumentPane();
  if (pane != null) {
    pane.split(PaneData.this,document,direction);
  }
}
