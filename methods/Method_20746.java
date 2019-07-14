@Override public void onActivityResumed(final @NonNull Activity activity){
  if (this.isInBackground) {
    this.koala.trackAppOpen();
    AppEventsLogger.activateApp(activity.getApplication());
    this.client.config().compose(Transformers.pipeApiErrorsTo(this::handleConfigApiError)).compose(Transformers.neverError()).subscribe(c -> this.config.config(c));
    final String accessToken=this.currentUser.getAccessToken();
    if (ObjectUtils.isNotNull(accessToken)) {
      this.client.fetchCurrentUser().compose(Transformers.neverError()).subscribe(u -> this.currentUser.refresh(u));
    }
    this.isInBackground=false;
  }
}
