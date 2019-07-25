/** 
 * Unlike default "contains" method this one returns true only if some of popups displayed on this layer contains the specified point. Popup layer itself is not taken into account and doesn't absorb any mouse events because of that.
 * @param x X coordinate
 * @param y Y coordinate
 * @return true if some of popups displayed on this popup later contains the specified point, false otherwise
 */
@Override public boolean contains(final int x,final int y){
  for (  final Component child : getComponents()) {
    final Point l=child.getLocation();
    if (child instanceof ShapeProvider) {
      final Shape shape=((ShapeProvider)child).provideShape();
      if (shape != null && shape.contains(x - l.x,y - l.y)) {
        return true;
      }
    }
 else {
      if (child.getBounds().contains(x,y)) {
        return true;
      }
    }
  }
  return false;
}
