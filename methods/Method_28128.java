@Override public void onShowUpdate(){
  hideProgress();
  Toasty.error(App.getInstance(),getString(R.string.new_version)).show();
  ConvenienceBuilder.createRateOnClickAction(this).onClick();
  finish();
}
