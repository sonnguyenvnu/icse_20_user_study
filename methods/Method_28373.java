@Override public void onShowHideFragment(@NonNull FragmentManager fragmentManager,@NonNull Fragment toShow,@NonNull Fragment toHide){
  fragmentManager.beginTransaction().hide(toHide).show(toShow).commit();
  toHide.onHiddenChanged(true);
  toShow.onHiddenChanged(false);
}
