private boolean canOpen(@NonNull FilesListModel item){
  if (item.getRawUrl() == null)   return false;
  if (item.getSize() > FileHelper.ONE_MB && !MarkDownProvider.isImage(item.getRawUrl())) {
    MessageDialogView.newInstance(getString(R.string.big_file),getString(R.string.big_file_description),false,true,Bundler.start().put(BundleConstant.YES_NO_EXTRA,true).put(BundleConstant.EXTRA,item.getRawUrl()).end()).show(getChildFragmentManager(),"MessageDialogView");
    return false;
  }
  return true;
}
