@UiThread void moveModel(int fromPosition,int toPosition){
  ArrayList<EpoxyModel<?>> updatedList=new ArrayList<>(getCurrentModels());
  updatedList.add(toPosition,updatedList.remove(fromPosition));
  notifyBlocker.allowChanges();
  notifyItemMoved(fromPosition,toPosition);
  notifyBlocker.blockChanges();
  boolean interruptedDiff=differ.forceListOverride(updatedList);
  if (interruptedDiff) {
    epoxyController.requestModelBuild();
  }
}
