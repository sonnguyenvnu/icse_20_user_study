@Override public void onActivityPaused(final @NonNull Activity activity){
  AppEventsLogger.deactivateApp(activity);
}
