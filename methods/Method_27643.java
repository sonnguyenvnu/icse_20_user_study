@OnClick(R.id.pinUnpin) void pinUpin(){
  if (PrefGetter.isProEnabled()) {
    getPresenter().onPinUnpinGist();
  }
 else {
    PremiumActivity.Companion.startActivity(this);
  }
}
