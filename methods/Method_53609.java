public void changeSelection(int delta){
  int length=values[POS].length;
  selected=(delta + selected + length) % length;
  mKeyCycleNo.setText("" + selected);
  update();
}
