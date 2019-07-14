@Override public void onRemove(@NonNull TimelineModel comment){
  hideProgress();
  adapter.removeItem(comment);
}
