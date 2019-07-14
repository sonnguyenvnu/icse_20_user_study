protected void selectHome(boolean hideRepo){
  Menu menu=getMainDrawerMenu();
  if (menu != null) {
    if (hideRepo) {
      menu.findItem(R.id.navToRepo).setVisible(false);
      menu.findItem(R.id.mainView).setVisible(true);
      return;
    }
    menu.findItem(R.id.navToRepo).setVisible(false);
    menu.findItem(R.id.mainView).setCheckable(true);
    menu.findItem(R.id.mainView).setChecked(true);
  }
}
