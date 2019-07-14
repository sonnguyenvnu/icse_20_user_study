@Override public void onAddAndHide(@NonNull FragmentManager fragmentManager,@NonNull Fragment toAdd,@NonNull Fragment toHide){
  fragmentManager.beginTransaction().hide(toHide).add(R.id.container,toAdd,toAdd.getClass().getSimpleName()).commit();
  if (toHide != null)   toHide.onHiddenChanged(true);
  if (toAdd != null)   toAdd.onHiddenChanged(false);
}
