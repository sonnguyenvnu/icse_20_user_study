/** 
 * set the content of the drawers stack, similar to  {@link JFXDrawer#setContent(Node)}
 * @param content
 */
public void setContent(Node content){
  this.content=content;
  if (drawers.size() > 0) {
    drawers.get(0).setContent(content);
  }
 else {
    getChildren().add(this.content);
  }
}
