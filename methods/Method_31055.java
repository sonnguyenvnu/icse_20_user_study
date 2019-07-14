@TargetApi(Build.VERSION_CODES.LOLLIPOP) public static void setupTransitionOnActivityCreated(Fragment fragment){
  if (!shouldEnableTransition()) {
    return;
  }
  setupTransitionForAppBar(fragment);
  ActivityCompat.startPostponedEnterTransition(fragment.getActivity());
}
