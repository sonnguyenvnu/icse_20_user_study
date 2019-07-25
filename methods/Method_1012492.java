void apply(){
  int i;
  for (i=0; i != einfocount; i++) {
    EditInfo ei=einfos[i];
    if (ei.textf != null && ei.text == null) {
      try {
        double d=parseUnits(ei);
        ei.value=d;
      }
 catch (      Exception ex) {
      }
    }
    if (ei.button != null)     continue;
    elm.setEditValue(i,ei);
    if (elm instanceof CircuitElm) {
      Adjustable adj=cframe.findAdjustable((CircuitElm)elm,i);
      if (adj != null)       adj.setSliderValue(ei.value);
    }
  }
  cframe.needAnalyze();
}
