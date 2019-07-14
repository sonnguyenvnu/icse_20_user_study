@TargetApi(Build.VERSION_CODES.LOLLIPOP) public static void setupTransitionForAppBar(Activity activity){
  if (!shouldEnableTransition()) {
    return;
  }
  View appbar=activity.findViewById(R.id.appBarWrapper);
  if (appbar != null) {
    appbar.setTransitionName(TRANSITION_NAME_APPBAR);
  }
}
