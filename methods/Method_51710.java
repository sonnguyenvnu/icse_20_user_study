/** 
 * Selects the given node in the AST.
 * @param node node to be selected
 * @param selector object which requests the selection
 */
public void selectNode(Node node,Object selector){
  fireViewerModelEvent(new ViewerModelEvent(selector,ViewerModelEvent.NODE_SELECTED,node));
}
