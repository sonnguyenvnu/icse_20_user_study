public void setOccupyStatusBar(boolean value){
  occupyStatusBar=value;
  if (actionMode != null) {
    actionMode.setPadding(0,occupyStatusBar ? AndroidUtilities.statusBarHeight : 0,0,0);
  }
}
