private void onAddFolders(AddFolderEvent event){
  final List<File> folders=event.folders;
  final List<Folder> existedFolders=mAdapter.getData();
  mPresenter.addFolders(folders,existedFolders);
}
