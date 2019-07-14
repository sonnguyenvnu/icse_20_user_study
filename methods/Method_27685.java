@Override public void onRequire2Fa(){
  Toasty.warning(App.getInstance(),getString(R.string.two_factors_otp_error)).show();
  twoFactor.setVisibility(View.VISIBLE);
  hideProgress();
}
