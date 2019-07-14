public void hideSelector(){
  if (currentChildView != null) {
    View child=currentChildView;
    onChildPressed(currentChildView,false);
    currentChildView=null;
    removeSelection(child,null);
  }
}
