@OnClick(R.id.detailsIcon) void onTitleClick(){
  if (getPresenter().getCommit() != null && !InputHelper.isEmpty(getPresenter().getCommit().getGitCommit().getMessage()))   MessageDialogView.newInstance(String.format("%s/%s",getPresenter().getLogin(),getPresenter().getRepoId()),getPresenter().getCommit().getGitCommit().getMessage(),true,false).show(getSupportFragmentManager(),MessageDialogView.TAG);
}
