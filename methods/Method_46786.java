public void exit(){
  if (backPressedToExitOnce) {
    SshConnectionPool.getInstance().expungeAllConnections();
    finish();
    if (isRootExplorer()) {
    }
  }
 else {
    this.backPressedToExitOnce=true;
    showToast(getString(R.string.pressagain));
    new Handler().postDelayed(() -> {
      backPressedToExitOnce=false;
    }
,2000);
  }
}
