@Override public void onShowDeleteMsg(long id){
  MessageDialogView.newInstance(getString(R.string.delete),getString(R.string.confirm_message),Bundler.start().put(BundleConstant.EXTRA,id).put(BundleConstant.YES_NO_EXTRA,false).end()).show(getChildFragmentManager(),MessageDialogView.TAG);
}
