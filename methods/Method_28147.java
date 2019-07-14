@Override public void onNavToRepoClicked(){
  Intent intent=ActivityHelper.editBundle(RepoPagerActivity.createIntent(this,getPresenter().getRepoId(),getPresenter().getLogin(),RepoPagerMvp.ISSUES),isEnterprise());
  startActivity(intent);
  finish();
}
