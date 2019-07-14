@Override public void onPlayListDeleted(PlayList playList){
  mAdapter.getData().remove(mDeleteIndex);
  mAdapter.notifyItemRemoved(mDeleteIndex);
  mAdapter.updateFooterView();
}
