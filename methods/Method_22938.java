public void updateMode(){
  Mode mode=editor.getMode();
  textColor[SELECTED]=mode.getColor("footer.text.selected.color");
  textColor[UNSELECTED]=mode.getColor("footer.text.unselected.color");
  font=mode.getFont("footer.text.font");
  tabColor[SELECTED]=mode.getColor("footer.tab.selected.color");
  tabColor[UNSELECTED]=mode.getColor("footer.tab.unselected.color");
  updateColor=mode.getColor("footer.updates.color");
  gradient=mode.makeGradient("footer",400,HIGH);
  bgColor=mode.getColor("footer.gradient.bottom");
  setBackground(bgColor);
}
