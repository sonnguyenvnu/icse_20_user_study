/** 
 * propagate any mouse events on the tab pane to its parent
 */
public void propagateMouseEventsToParent(){
  this.addEventHandler(MouseEvent.ANY,e -> {
    e.consume();
    this.getParent().fireEvent(e);
  }
);
}
