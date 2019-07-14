@TargetApi(Build.VERSION_CODES.LOLLIPOP) public static Bundle makeActivityOptionsBundle(Activity activity,View... sharedViews){
  if (!shouldEnableTransition()) {
    return null;
  }
  ArrayList<Pair<View,String>> sharedElementList=new ArrayList<>();
  for (  View sharedView : sharedViews) {
    sharedElementList.add(Pair.create(sharedView,sharedView.getTransitionName()));
  }
  View appbar=activity.findViewById(R.id.appBarWrapper);
  if (appbar != null) {
    sharedElementList.add(Pair.create(appbar,appbar.getTransitionName()));
  }
  Pair<View,String>[] sharedElements=sharedElementList.toArray(new Pair[sharedElementList.size()]);
  return ActivityOptionsCompat.makeSceneTransitionAnimation(activity,sharedElements).toBundle();
}
