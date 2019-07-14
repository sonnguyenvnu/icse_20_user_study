@Override public void openUserProfile(){
  UserPagerActivity.startActivity(this,Login.getUser().getLogin(),false,PrefGetter.isEnterprise(),-1);
}
