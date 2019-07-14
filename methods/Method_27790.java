@Override public void onDeletePinnedIssue(long id,int position){
  MessageDialogView.newInstance(getString(R.string.delete),getString(R.string.confirm_message),Bundler.start().put(BundleConstant.YES_NO_EXTRA,true).put(BundleConstant.EXTRA,position).put(BundleConstant.ID,id).end()).show(getChildFragmentManager(),MessageDialogView.TAG);
}
