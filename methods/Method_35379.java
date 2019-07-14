private void storeSnapshot(){
  FileTreeStack.FileTreeSnapshot snapshot=new FileTreeStack.FileTreeSnapshot();
  snapshot.parent=mFileParent;
  snapshot.files=mFiles;
  snapshot.scrollOffset=recyclerView.computeVerticalScrollOffset();
  mFileTreeStack.push(snapshot);
}
