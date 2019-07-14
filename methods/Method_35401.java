@Override public void onFolderUpdated(Folder folder){
  mAdapter.getData().set(mUpdateIndex,folder);
  mAdapter.notifyItemChanged(mUpdateIndex);
}
