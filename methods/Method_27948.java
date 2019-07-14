@Override public void onNavToRepoClicked(){
  NameParser nameParser=new NameParser("");
  nameParser.setName(getPresenter().getRepoId());
  nameParser.setUsername(getPresenter().getLogin());
  nameParser.setEnterprise(isEnterprise());
  RepoPagerActivity.startRepoPager(this,nameParser);
  finish();
}
