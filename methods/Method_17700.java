/** 
 * @return the x or y position of the given view relative to the LithoView that this ComponentTreeis being rendered in to.
 */
private static float getPositionRelativeToLithoView(View mountContent,boolean getX){
  float pos=0;
  View currentView=mountContent;
  while (true) {
    if (currentView == null) {
      throw new RuntimeException("Got unexpected null parent");
    }
    if (currentView instanceof LithoView) {
      return pos;
    }
    pos+=getX ? currentView.getX() : currentView.getY();
    if (!(currentView.getParent() instanceof View)) {
      throw new RuntimeException("Expected parent to be View, was " + currentView.getParent());
    }
    currentView=(View)currentView.getParent();
  }
}
