void apply(){
  int i;
  for (i=0; i != einfocount; i++) {
    Adjustable adj=findAdjustable(i);
    if (adj == null)     continue;
    EditInfo ei=einfos[i];
    try {
      adj.sliderText=ei.labelBox.getText();
      adj.label.setText(adj.sliderText);
      double d=EditDialog.parseUnits(ei,ei.minBox.getText());
      adj.minValue=d;
      d=EditDialog.parseUnits(ei,ei.maxBox.getText());
      adj.maxValue=d;
      adj.setSliderValue(ei.value);
    }
 catch (    Exception e) {
    }
  }
}
