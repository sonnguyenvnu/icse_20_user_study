@Override public void onPlayListsLoaded(List<PlayList> playLists){
  mAdapter.setData(playLists);
  mAdapter.notifyDataSetChanged();
}
