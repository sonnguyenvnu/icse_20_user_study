@OnClick({R.id.startGist,R.id.forkGist,R.id.browser}) public void onGistActions(View view){
  if (getPresenter().getGist() == null)   return;
  if (view.getId() != R.id.browser) {
    view.setEnabled(false);
  }
switch (view.getId()) {
case R.id.startGist:
    GithubActionService.startForGist(this,getPresenter().getGist().getGistId(),getPresenter().isStarred() ? GithubActionService.UNSTAR_GIST : GithubActionService.STAR_GIST,isEnterprise());
  getPresenter().onStarGist();
break;
case R.id.forkGist:
GithubActionService.startForGist(this,getPresenter().getGist().getGistId(),GithubActionService.FORK_GIST,isEnterprise());
getPresenter().onForkGist();
break;
case R.id.browser:
ActivityHelper.startCustomTab(this,getPresenter().getGist().getHtmlUrl());
break;
}
}
