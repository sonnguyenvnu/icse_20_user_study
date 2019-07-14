@Override public void onDeleteFile(@NonNull FilesListModel item,int position){
  MessageDialogView.newInstance(getString(R.string.delete),getString(R.string.confirm_message),false,Bundler.start().put(BundleConstant.ID,position).put(BundleConstant.YES_NO_EXTRA,true).end()).show(getChildFragmentManager(),MessageDialogView.TAG);
}
