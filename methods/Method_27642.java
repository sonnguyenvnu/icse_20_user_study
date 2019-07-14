@OnClick(R.id.edit) void onEdit(){
  if (PrefGetter.isProEnabled() || PrefGetter.isAllFeaturesUnlocked()) {
    if (getPresenter().getGist() != null)     CreateGistActivity.start(this,getPresenter().getGist());
  }
 else {
    PremiumActivity.Companion.startActivity(this);
  }
}
