public void trackActivityTapped(final @NonNull Activity activity){
  this.client.track(KoalaEvent.ACTIVITY_VIEW_ITEM,KoalaUtils.activityProperties(activity,this.client.loggedInUser()));
}
