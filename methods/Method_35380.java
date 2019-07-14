private void restoreSnapshot(FileTreeStack.FileTreeSnapshot snapshot){
  final File parent=snapshot.parent;
  final List<FileWrapper> files=snapshot.files;
  final int scrollOffset=snapshot.scrollOffset;
  mFileParent=parent;
  mFiles=files;
  final int oldScrollOffset=recyclerView.computeVerticalScrollOffset();
  toolbar.setTitle(getToolbarTitle(parent));
  mAdapter.setData(files);
  mAdapter.notifyDataSetChanged();
  toggleEmptyViewVisibility();
  recyclerView.scrollBy(0,scrollOffset - oldScrollOffset);
}
