public void updateMode(){
  Mode mode=editor.getMode();
  textColor[SELECTED]=mode.getColor("header.text.selected.color");
  textColor[UNSELECTED]=mode.getColor("header.text.unselected.color");
  font=mode.getFont("header.text.font");
  tabColor[SELECTED]=mode.getColor("header.tab.selected.color");
  tabColor[UNSELECTED]=mode.getColor("header.tab.unselected.color");
  arrowColor=mode.getColor("header.tab.arrow.color");
  modifiedColor=mode.getColor("header.tab.modified.color");
  gradient=mode.makeGradient("header",400,HIGH);
}
