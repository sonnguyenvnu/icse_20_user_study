/** 
 * highlights the matching text in the specified pane
 * @param pane node to search into its text
 * @param query search text
 */
public synchronized void highlight(Parent pane,String query){
  if (this.parent != null && !boxes.isEmpty()) {
    clear();
  }
  if (query == null || query.isEmpty())   return;
  this.parent=pane;
  Set<Node> nodes=getTextNodes(pane);
  ArrayList<Rectangle> allRectangles=new ArrayList<>();
  for (  Node node : nodes) {
    Text text=((Text)node);
    final int beginIndex=text.getText().toLowerCase().indexOf(query.toLowerCase());
    if (beginIndex > -1 && node.impl_isTreeVisible()) {
      ArrayList<Bounds> boundingBoxes=getMatchingBounds(query,text);
      ArrayList<Rectangle> rectangles=new ArrayList<>();
      for (      Bounds boundingBox : boundingBoxes) {
        HighLightRectangle rect=new HighLightRectangle(text);
        rect.setCacheHint(CacheHint.SPEED);
        rect.setCache(true);
        rect.setMouseTransparent(true);
        rect.setBlendMode(BlendMode.MULTIPLY);
        rect.fillProperty().bind(paintProperty());
        rect.setManaged(false);
        rect.setX(boundingBox.getMinX());
        rect.setY(boundingBox.getMinY());
        rect.setWidth(boundingBox.getWidth());
        rect.setHeight(boundingBox.getHeight());
        rectangles.add(rect);
        allRectangles.add(rect);
      }
      boxes.put(node,rectangles);
    }
  }
  JFXUtilities.runInFXAndWait(() -> getParentChildren(pane).addAll(allRectangles));
}
