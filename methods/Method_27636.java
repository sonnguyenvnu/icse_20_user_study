@Override public void onItemClick(int position,View v,FilesListModel item){
  if (getView() != null) {
    if (v.getId() == R.id.delete) {
      getView().onDeleteFile(item,position);
    }
 else     if (v.getId() == R.id.edit) {
      getView().onEditFile(item,position);
    }
 else {
      getView().onOpenFile(item,position);
    }
  }
}
