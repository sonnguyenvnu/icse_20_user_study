@Override public void onUserIsBlackListed(){
  Toast.makeText(App.getInstance(),"You are blacklisted, please contact the dev",Toast.LENGTH_LONG).show();
  finish();
}
