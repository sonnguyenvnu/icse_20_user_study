void updateUIelements(){
  inCallBack=true;
  int max=(int)(0.5 + 100 * ((selected == values[POS].length - 1) ? 1 : (values[POS][selected + 1])));
  int min=(int)(0.5 + 100 * (selected == 0 ? 0 : (values[POS][selected - 1])));
  boolean middle=(selected > 0) && (selected < values[POS].length - 1);
  mPos.setEnabled(middle);
  delete.setEnabled(middle);
  mPos.setMaximum(max - 1);
  mPos.setMinimum(min + 1);
  if (CycleView.MainAttribute.mapTo100[mAttrIndex]) {
    mAmp.setMinimum(0);
    mAmp.setMaximum(100);
    mOff.setMinimum(-100);
    mOff.setMaximum(100);
    Hashtable<Integer,JLabel> labelTable=new Hashtable<Integer,JLabel>();
    labelTable.put(0,new JLabel("" + CycleView.MainAttribute.typicalRange[mAttrIndex][0]));
    labelTable.put(50,new JLabel("" + (CycleView.MainAttribute.typicalRange[mAttrIndex][0] + CycleView.MainAttribute.typicalRange[mAttrIndex][1]) / 2));
    labelTable.put(100,new JLabel("" + CycleView.MainAttribute.typicalRange[mAttrIndex][1]));
    mOff.setLabelTable(labelTable);
    mAmp.setLabelTable(labelTable);
  }
 else {
    mAmp.setMinimum((int)CycleView.MainAttribute.typicalRange[mAttrIndex][0]);
    mAmp.setMaximum((int)CycleView.MainAttribute.typicalRange[mAttrIndex][1]);
    mOff.setMinimum((int)CycleView.MainAttribute.typicalRange[mAttrIndex][0]);
    mOff.setMaximum((int)CycleView.MainAttribute.typicalRange[mAttrIndex][1]);
    mOff.setLabelTable(null);
    mAmp.setLabelTable(null);
  }
  if (CycleView.MainAttribute.mapTo100[mAttrIndex]) {
    mAmp.setPaintTicks(true);
    mAmp.setPaintLabels(true);
    mAmp.setMinorTickSpacing(10);
    mAmp.setMajorTickSpacing(100);
    mOff.setMinorTickSpacing(10);
    mOff.setMajorTickSpacing(100);
    mAmp.repaint();
  }
 else {
    int maxr=(int)(CycleView.MainAttribute.typicalRange[mAttrIndex][1]);
    mAmp.setPaintTicks(true);
    mAmp.setPaintLabels(true);
    mAmp.setMinorTickSpacing(90);
    mAmp.setMajorTickSpacing(180);
    mAmp.repaint();
    mOff.setMinorTickSpacing(90);
    mOff.setMajorTickSpacing(180);
    mAmp.setPaintTicks(true);
    mAmp.setPaintLabels(true);
  }
  mPos.setValue((int)(values[POS][selected] * 100));
  mPeriod.setValue((int)(values[PERIOD][selected]));
  if (CycleView.MainAttribute.mapTo100[mAttrIndex]) {
    float range_min=CycleView.MainAttribute.typicalRange[mAttrIndex][0];
    float range_max=CycleView.MainAttribute.typicalRange[mAttrIndex][1];
    mAmp.setValue((int)(0.5 + 100 * (values[AMP][selected] / (range_max - range_min) + range_min)));
    mOff.setValue((int)(0.5 + 100 * (values[OFFSET][selected] / (range_max - range_min) + range_min)));
  }
 else {
    mAmp.setValue((int)(0.5 + values[AMP][selected]));
    mOff.setValue((int)(0.5 + values[OFFSET][selected]));
  }
  inCallBack=false;
}
