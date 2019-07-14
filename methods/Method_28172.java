@Override public void onRemove(@NonNull TimelineModel timelineModel){
  hideProgress();
  adapter.removeItem(timelineModel);
}
