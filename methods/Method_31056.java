@TargetApi(Build.VERSION_CODES.LOLLIPOP) public static void setupTransitionForAppBar(Fragment fragment){
  if (!shouldEnableTransition()) {
    return;
  }
  View appbar=fragment.getView().findViewById(R.id.appBarWrapper);
  if (appbar != null) {
    appbar.setTransitionName(TRANSITION_NAME_APPBAR);
  }
}
