/** 
 * propagate mouse events to the parent node ( e.g. to allow dragging while clicking on the list)
 */
public void propagateMouseEventsToParent(){
  this.addEventHandler(MouseEvent.ANY,e -> {
    e.consume();
    this.getParent().fireEvent(e);
  }
);
}
