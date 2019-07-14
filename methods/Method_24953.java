private void createColorBoxesForLights(){
  Pattern p=Pattern.compile("ambientLight[\\(\\s]|directionalLight[\\(\\s]" + "|pointLight[\\(\\s]|spotLight[\\(\\s]|lightSpecular[\\(\\s]" + "|specular[\\(\\s]|ambient[\\(\\s]|emissive[\\(\\s]");
  for (int i=0; i < codeTabs.length; i++) {
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
      int colorParamsEnd=openPar;
      int commas=3;
      while (commas-- > 0) {
        colorParamsEnd=tab.indexOf(",",colorParamsEnd + 1);
        if (colorParamsEnd < 0 || colorParamsEnd > closePar) {
          colorParamsEnd=closePar;
          break;
        }
      }
      for (      Handle handle : allHandles.get(i)) {
        if (handle.startChar > openPar && handle.endChar <= colorParamsEnd) {
          colorHandles.add(handle);
        }
      }
      if (colorHandles.size() > 0) {
        String insidePar=tab.substring(openPar + 1,colorParamsEnd);
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
              colorBoxes.get(i).add(newCCB);
            }
          }
 else {
            colorBoxes.get(i).add(newCCB);
          }
        }
      }
    }
  }
}
