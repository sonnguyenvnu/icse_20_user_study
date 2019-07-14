@Override public void onLogoutPressed(){
  MessageDialogView.newInstance(getString(R.string.logout),getString(R.string.confirm_message),Bundler.start().put(BundleConstant.YES_NO_EXTRA,true).put("logout",true).end()).show(getSupportFragmentManager(),MessageDialogView.TAG);
}
