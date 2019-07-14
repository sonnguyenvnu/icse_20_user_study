protected void showNavToRepoItem(){
  Menu menu=getMainDrawerMenu();
  if (menu != null) {
    menu.findItem(R.id.navToRepo).setVisible(true);
  }
}
