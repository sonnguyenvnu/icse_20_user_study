/** 
 * propagate any mouse event on the tree table view to its parent
 */
public void propagateMouseEventsToParent(){
  this.addEventHandler(MouseEvent.ANY,e -> {
    e.consume();
    this.getParent().fireEvent(e);
  }
);
}
