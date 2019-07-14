public void setOffset(){
  if (inCallBack) {
    return;
  }
  if (CycleView.MainAttribute.mapTo100[mAttrIndex]) {
    float min=CycleView.MainAttribute.typicalRange[mAttrIndex][0];
    float max=CycleView.MainAttribute.typicalRange[mAttrIndex][1];
    values[OFFSET][selected]=mOff.getValue() * (max - min) / 100 + min;
  }
 else {
    values[OFFSET][selected]=mOff.getValue();
  }
  update();
}
