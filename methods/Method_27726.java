@Override public void onAddAndHide(@NonNull FragmentManager fragmentManager,@NonNull Fragment toAdd,@NonNull Fragment toHide){
  toHide.onHiddenChanged(true);
  fragmentManager.beginTransaction().hide(toHide).add(R.id.container,toAdd,toAdd.getClass().getSimpleName()).commit();
  toAdd.onHiddenChanged(false);
}
