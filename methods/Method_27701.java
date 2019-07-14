private void onProceed(@NonNull String productKey){
  if (AppHelper.isGoogleAvailable(this)) {
    DonateActivity.Companion.start(this,productKey,null,null);
  }
 else {
    showErrorMessage(getString(R.string.google_play_service_error));
  }
}
