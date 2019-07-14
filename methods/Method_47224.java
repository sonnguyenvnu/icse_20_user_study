public void selectActionView(MenuItem item){
  if (lastItemSelected != null) {
    lastItemSelected.setColorFilter(idleIconColor);
  }
  if (item.getActionView() != null) {
    lastItemSelected=(ImageButton)item.getActionView();
    lastItemSelected.setColorFilter(selectedIconColor);
  }
}
