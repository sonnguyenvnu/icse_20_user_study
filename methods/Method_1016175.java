/** 
 * Splits document's pane into two panes using the specified direction to decide split settings.
 * @param movedDocument document that should be moved to new pane
 * @param direction     split direction
 */
public void split(final T movedDocument,final int direction){
  final PaneData<T> pane=getPane(movedDocument);
  if (pane != null) {
    split(pane,movedDocument,direction);
  }
}
