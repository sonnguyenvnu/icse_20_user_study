public void setSelectionVisibility(boolean visible){
  if (selectionView == null) {
    return;
  }
  selectionView.setVisibility(visible ? VISIBLE : GONE);
}
