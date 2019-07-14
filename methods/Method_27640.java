@OnClick(R.id.detailsIcon) void onTitleClick(){
  if (getPresenter().getGist() != null && !InputHelper.isEmpty(getPresenter().getGist().getDescription()))   MessageDialogView.newInstance(getString(R.string.details),getPresenter().getGist().getDescription(),false,true).show(getSupportFragmentManager(),MessageDialogView.TAG);
}
