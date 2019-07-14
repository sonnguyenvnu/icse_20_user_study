@Override public void onPlayListEdited(PlayList playList){
  mAdapter.getData().set(mEditIndex,playList);
  mAdapter.notifyItemChanged(mEditIndex);
  mAdapter.updateFooterView();
}
