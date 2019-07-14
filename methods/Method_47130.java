public void updateTabWithDb(Tab tab){
  CURRENT_PATH=tab.path;
  home=tab.home;
  loadlist(CURRENT_PATH,false,OpenMode.UNKNOWN);
}
