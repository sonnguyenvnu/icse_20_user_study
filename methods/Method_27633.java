@Override public void onMessageDialogActionClicked(boolean isOk,@Nullable Bundle bundle){
  super.onMessageDialogActionClicked(isOk,bundle);
  if (isOk && bundle != null) {
    String url=bundle.getString(BundleConstant.EXTRA);
    if (!InputHelper.isEmpty(url)) {
      if (ActivityHelper.checkAndRequestReadWritePermission(getActivity())) {
        RestProvider.downloadFile(getContext(),url);
      }
    }
 else     if (bundle.getBoolean(BundleConstant.YES_NO_EXTRA)) {
      if (adapter != null) {
        int position=bundle.getInt(BundleConstant.ID);
        FilesListModel file=adapter.getItem(position);
        if (file != null) {
          if (getPresenter().getFilesMap().get(file.getFilename()) != null) {
            file=getPresenter().getFilesMap().get(file.getFilename());
            file.setContent(null);
            getPresenter().getFilesMap().put(file.getFilename(),file);
          }
        }
        adapter.removeItem(position);
      }
    }
  }
}
