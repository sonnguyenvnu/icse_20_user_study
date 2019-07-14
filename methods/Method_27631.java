@Override public void onInitFiles(@Nullable ArrayList<FilesListModel> filesListModel,boolean isOwner){
  if (filesListModel == null) {
    filesListModel=new ArrayList<>();
  }
  if (getPresenter().getFilesMap().isEmpty()) {
    for (    FilesListModel listModel : filesListModel) {
      getPresenter().getFilesMap().put(listModel.getFilename(),listModel);
    }
  }
  adapter.setOwner(isOwner);
  getPresenter().onSetList(filesListModel);
  adapter.insertItems(filesListModel);
}
