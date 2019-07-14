private boolean isTabInForeground(OpenFile open){
  String title=open.name;
  int selectedIndex=house.getSelectedIndex();
  return (selectedIndex >= 0 && selectedIndex == house.indexOfTab(title));
}
