@Override public void onFoldersLoaded(List<Folder> folders){
  mAdapter.setData(folders);
  mAdapter.notifyDataSetChanged();
}
