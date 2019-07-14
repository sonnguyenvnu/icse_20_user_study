@Override public void onNavToRepoClicked(){
  Intent intent=ActivityHelper.editBundle(RepoPagerActivity.createIntent(this,getPresenter().getRepoId(),getPresenter().getLogin(),RepoPagerMvp.PULL_REQUEST),isEnterprise());
  startActivity(intent);
  finish();
}
