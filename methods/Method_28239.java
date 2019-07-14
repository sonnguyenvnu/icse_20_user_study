@OnClick(R.id.cancelReview) void onCancelReviews(View view){
  MessageDialogView.newInstance(getString(R.string.cancel_reviews),getString(R.string.confirm_message),false,Bundler.start().put(BundleConstant.YES_NO_EXTRA,true).put(BundleConstant.EXTRA_TYPE,true).end()).show(getSupportFragmentManager(),MessageDialogView.TAG);
}
