private ArrayList<ColorMode> findAllColorModes(){
  ArrayList<ColorMode> modes=new ArrayList<>();
  for (int i=0; i < codeTabs.length; i++) {
    String tab=codeTabs[i];
    int index=-1;
    while ((index=tab.indexOf("colorMode",index + 1)) > -1) {
      if (isInRangeList(index,commentBlocks.get(i))) {
        continue;
      }
      index+=9;
      int parOpen=tab.indexOf('(',index);
      if (parOpen < 0) {
        continue;
      }
      int parClose=tab.indexOf(')',parOpen + 1);
      if (parClose < 0) {
        continue;
      }
      String modeDesc=tab.substring(parOpen + 1,parClose);
      String context=getObject(index - 9,tab);
      modes.add(ColorMode.fromString(context,modeDesc));
    }
  }
  return modes;
}
