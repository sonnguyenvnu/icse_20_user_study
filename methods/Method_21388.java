@OnClick(R.id.rate_button) protected void rateButtonClick(){
  this.koala.trackAppRatingNow();
  this.hasSeenAppRatingPreference.set(true);
  dismiss();
  ViewUtils.openStoreRating(getContext(),getContext().getPackageName());
}
