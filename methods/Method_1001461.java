public void swimlane(String name,HtmlColor color,Display label){
  currentSwimlane=getOrCreate(name);
  if (color != null) {
    currentSwimlane.setSpecificColorTOBEREMOVED(ColorType.BACK,color);
  }
  if (Display.isNull(label) == false) {
    currentSwimlane.setDisplay(label);
  }
}
