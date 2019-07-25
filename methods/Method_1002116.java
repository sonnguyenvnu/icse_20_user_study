private void start(){
  ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
  NetworkInfo networkInfo=cm.getActiveNetworkInfo();
  if (networkInfo != null && networkInfo.isConnected()) {
    if (LastCheckinInfo.read(this).androidId == 0) {
      new Thread(() -> {
        Runnable next;
        next=checkin(false) ? this::loadLoginPage : () -> showError(R.string.auth_general_error_desc);
        LoginActivity.this.runOnUiThread(next);
      }
).start();
    }
 else {
      loadLoginPage();
    }
  }
 else {
    showError(R.string.no_network_error_desc);
  }
}
