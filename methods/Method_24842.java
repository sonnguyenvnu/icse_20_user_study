private int clipTabIndex(int tabIndex){
  return PApplet.constrain(tabIndex,0,tabStartOffsets.length - 1);
}
