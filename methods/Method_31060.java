@TargetApi(Build.VERSION_CODES.LOLLIPOP) public static void setEnterReturnExplode(Fragment fragment){
  if (!shouldEnableTransition()) {
    return;
  }
  Window window=fragment.getActivity().getWindow();
  Transition explode=new Explode().excludeTarget(android.R.id.statusBarBackground,true).excludeTarget(android.R.id.navigationBarBackground,true);
  window.setEnterTransition(explode);
  window.setReturnTransition(explode);
}
