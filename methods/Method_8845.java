public void select(ViewGroup selectionContainer){
  SelectionView selectionView=createSelectionView();
  this.selectionView=selectionView;
  selectionContainer.addView(selectionView);
  selectionView.updatePosition();
}
