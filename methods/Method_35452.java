@Override public void onPlayListCreated(PlayList playList){
  mAdapter.getData().add(playList);
  mAdapter.notifyItemInserted(mAdapter.getData().size() - 1);
  mAdapter.updateFooterView();
}
