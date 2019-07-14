public final void onActivityStarted(@NonNull Activity activity){
  isActivityStopped=false;
  for (  RouterTransaction transaction : backstack) {
    transaction.controller.activityStarted(activity);
    for (    Router childRouter : transaction.controller.getChildRouters()) {
      childRouter.onActivityStarted(activity);
    }
  }
}
