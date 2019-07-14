private void onFilesLoaded(File parent,List<FileWrapper> files){
  mFileParent=parent;
  mFiles=files;
  mAdapter.setData(files);
  mAdapter.notifyDataSetChanged();
  recyclerView.scrollTo(0,0);
}
