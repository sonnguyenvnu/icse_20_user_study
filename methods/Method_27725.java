@Override public void onShowHideFragment(@NonNull FragmentManager fragmentManager,@NonNull Fragment toShow,@NonNull Fragment toHide){
  toHide.onHiddenChanged(true);
  fragmentManager.beginTransaction().hide(toHide).show(toShow).commit();
  toShow.onHiddenChanged(false);
}
