@Override public void onBind(){
  if (this.isLoggedIn) {
    this.discoverProjectsButton.setVisibility(View.VISIBLE);
    this.loginButton.setVisibility(View.GONE);
  }
 else {
    this.discoverProjectsButton.setVisibility(View.GONE);
    this.loginButton.setVisibility(View.VISIBLE);
  }
}
