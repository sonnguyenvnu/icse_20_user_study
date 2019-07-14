@Override public boolean animateRemove(RecyclerView.ViewHolder holder){
  dispatchRemoveStarting(holder);
  dispatchRemoveFinished(holder);
  return true;
}
