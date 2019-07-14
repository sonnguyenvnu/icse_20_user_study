public void setAmp(){
  if (inCallBack) {
    return;
  }
  int val=mAmp.getValue();
  if (CycleView.MainAttribute.mapTo100[mAttrIndex]) {
    float min=CycleView.MainAttribute.typicalRange[mAttrIndex][0];
    float max=CycleView.MainAttribute.typicalRange[mAttrIndex][1];
    values[AMP][selected]=val * (max - min) / 100 + min;
  }
 else {
    values[AMP][selected]=val;
  }
  update();
}
