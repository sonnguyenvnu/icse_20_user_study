@Override public void onSetHeader(@NonNull TimelineModel timelineModel){
  if (adapter != null) {
    if (adapter.isEmpty()) {
      adapter.addItem(timelineModel,0);
    }
 else {
      adapter.swapItem(timelineModel,0);
    }
  }
}
