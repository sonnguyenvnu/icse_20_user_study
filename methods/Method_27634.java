@Override public void onFileAdded(@NonNull FilesListModel file,Integer position){
  if (position == null || position == -1) {
    adapter.addItem(file);
    getPresenter().getFilesMap().put(file.getFilename(),file);
  }
 else {
    FilesListModel current=adapter.getItem(position);
    if (getPresenter().getFilesMap().get(current.getFilename()) != null) {
      FilesListModel toUpdate=getPresenter().getFilesMap().get(current.getFilename());
      toUpdate.setFilename(file.getFilename());
      toUpdate.setContent(file.getContent());
      getPresenter().getFilesMap().put(current.getFilename(),toUpdate);
    }
    adapter.swapItem(file,position);
  }
}
