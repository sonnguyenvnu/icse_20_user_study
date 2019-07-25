public void execute(){
  elm.sim.analyzeFlag=true;
  if (settingValue)   return;
  EditInfo ei=elm.getEditInfo(editItem);
  ei.value=getSliderValue();
  elm.setEditValue(editItem,ei);
  elm.sim.repaint();
}
