private void createColorBoxes(){
  colorBoxes=new ArrayList<>();
  Pattern p=Pattern.compile("color\\(|color\\s\\(|fill[\\(\\s]|stroke[\\(\\s]|background[\\(\\s]|tint[\\(\\s]");
  for (int i=0; i < codeTabs.length; i++) {
    List<ColorControlBox> colorBox=new ArrayList<>();
    colorBoxes.add(colorBox);
    String tab=codeTabs[i];
    Matcher m=p.matcher(tab);
    while (m.find()) {
      ArrayList<Handle> colorHandles=new ArrayList<>();
      int openPar=tab.indexOf("(",m.start());
      int closePar=tab.indexOf(")",m.end());
      if (openPar < 0 || closePar < 0) {
        continue;
      }
      if (isInRangeList(m.start(),commentBlocks.get(i))) {
        continue;
      }
      if (isInRangeList(m.start(),ignoreFunctions.get(i))) {
        continue;
      }
      for (      Handle handle : allHandles.get(i)) {
        if (handle.startChar > openPar && handle.endChar <= closePar) {
          colorHandles.add(handle);
        }
      }
      if (colorHandles.size() > 0) {
        String insidePar=tab.substring(openPar + 1,closePar);
        for (        Handle h : colorHandles) {
          insidePar=insidePar.replaceFirst(h.strValue,"");
        }
        boolean garbage=false;
        for (int j=0; j < insidePar.length(); j++) {
          if (insidePar.charAt(j) != ' ' && insidePar.charAt(j) != ',') {
            garbage=true;
          }
        }
        if (!garbage) {
          String context=getObject(m.start(),tab);
          ColorMode cmode=getColorModeForContext(context);
          ColorControlBox newCCB=new ColorControlBox(context,cmode,colorHandles);
          if (cmode.unrecognizedMode) {
            if (newCCB.isHex) {
              colorBox.add(newCCB);
            }
          }
 else {
            colorBox.add(newCCB);
          }
        }
      }
    }
  }
}
