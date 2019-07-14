@Override public void onSongDeleted(Song song){
  mAdapter.notifyItemRemoved(mDeleteIndex);
  mAdapter.updateSummaryText();
}
