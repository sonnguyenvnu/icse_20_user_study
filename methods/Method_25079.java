public void showButtonInMenu(boolean animate){
  if (getVisibility() == VISIBLE)   return;
  setVisibility(INVISIBLE);
  show(animate);
  Label label=getLabelView();
  if (label != null) {
    label.show(animate);
  }
}
