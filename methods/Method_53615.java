public void setAttr(int selectedIndex){
  int old=mAttrIndex;
  mAttrIndex=selectedIndex;
  float old_min=CycleView.MainAttribute.typicalRange[old][0];
  float old_max=CycleView.MainAttribute.typicalRange[old][1];
  float new_min=CycleView.MainAttribute.typicalRange[mAttrIndex][0];
  float new_max=CycleView.MainAttribute.typicalRange[mAttrIndex][1];
  for (int i=0; i < values[AMP].length; i++) {
    double value=values[AMP][i];
    values[AMP][i]=((new_max - new_min) * ((value - old_min) / (old_max - old_min))) + new_min;
  }
  update();
}
