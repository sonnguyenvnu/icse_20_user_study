public void removeMenuButton(FloatingActionButton fab){
  removeView(fab.getLabelView());
  removeView(fab);
  mButtonsCount--;
}
