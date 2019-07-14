@Override public void onOpenProfile(){
  UserPagerActivity.startActivity(this,Login.getUser().getLogin(),false,PrefGetter.isEnterprise(),-1);
}
