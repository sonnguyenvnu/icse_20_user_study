@Override public void onFolderDeleted(Folder folder){
  mAdapter.getData().remove(mDeleteIndex);
  mAdapter.notifyItemRemoved(mDeleteIndex);
  mAdapter.updateFooterView();
}
