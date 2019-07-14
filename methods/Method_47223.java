public void deselectCurrentActionView(){
  if (lastItemSelected != null) {
    lastItemSelected.setColorFilter(idleIconColor);
    lastItemSelected=null;
  }
}
