@Override public void onMenuItemSelect(@IdRes int id,int position,boolean fromUser){
  if (getView() != null) {
    getView().onNavigationChanged(position);
  }
}
