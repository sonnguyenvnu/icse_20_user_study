@Override public void onChangeWatchedCount(boolean isWatched){
  long count=InputHelper.toLong(watchRepo);
  watchRepo.setText(numberFormat.format(isWatched ? (count + 1) : (count > 0 ? (count - 1) : 0)));
  updatePinnedRepo();
}
