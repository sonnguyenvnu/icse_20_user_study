public void process(Style s,SwitchProcessor sp,PPr pPr,OSwitch oSwitch){
  int cutOff=9;
  if (oSwitch != null && oSwitch.fieldArgument != null) {
    cutOff=oSwitch.getEndLevel();
  }
  int level=getOutlineLvl(pPr,sp,s,cutOff);
  if (level == 9) {
    sp.proceed(false);
  }
 else {
    TocEntry te=sp.getEntry();
    te.setEntryLevel(level);
    sp.setStyleFound(true);
  }
}
