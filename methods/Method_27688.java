@Override public void onSuccessfullyLoggedIn(boolean extraLogin){
  checkPurchases(() -> {
    hideProgress();
    onRestartApp();
  }
);
}
