protected void selectMenuItem(@IdRes int id){
  Menu menu=getMainDrawerMenu();
  if (menu != null) {
    menu.findItem(id).setCheckable(true);
    menu.findItem(id).setChecked(true);
  }
}
