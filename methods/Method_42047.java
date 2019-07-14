public void invalidateSelectedCount(){
  int c=0;
  for (  Album m : this.albums) {
    c+=m.isSelected() ? 1 : 0;
  }
  this.selectedCount=c;
  if (this.selectedCount == 0)   stopSelection();
 else {
    this.actionsListener.onSelectionCountChanged(selectedCount,albums.size());
  }
}
