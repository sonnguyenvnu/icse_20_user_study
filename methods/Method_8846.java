public void deselect(){
  if (selectionView == null) {
    return;
  }
  if (selectionView.getParent() != null) {
    ((ViewGroup)selectionView.getParent()).removeView(selectionView);
  }
  selectionView=null;
}
