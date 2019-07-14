private void notifySelected(boolean increase){
  selectedCount+=increase ? 1 : -1;
  actionsListener.onSelectionCountChanged(selectedCount,getItemCount());
  if (selectedCount == 0 && isSelecting)   stopSelection();
 else   if (selectedCount > 0 && !isSelecting)   startSelection();
}
