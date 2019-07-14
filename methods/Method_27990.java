@OnClick(R.id.downloadRepoFiles) void onDownloadRepoFiles(){
  if (InputHelper.isEmpty(ref)) {
    ref=getPresenter().getDefaultBranch();
  }
  if (ActivityHelper.checkAndRequestReadWritePermission(getActivity())) {
    MessageDialogView.newInstance(getString(R.string.download),getString(R.string.confirm_message),Bundler.start().put(BundleConstant.YES_NO_EXTRA,true).end()).show(getChildFragmentManager(),MessageDialogView.TAG);
  }
}
