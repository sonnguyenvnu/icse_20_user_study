@Override public void onLocalMusicLoaded(List<Song> songs){
  mAdapter.setData(songs);
  mAdapter.notifyDataSetChanged();
}
