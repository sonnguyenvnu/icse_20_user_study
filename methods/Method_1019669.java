public void process(){
  APBufferedRegion p=null;
  String itemNameList=null;
  int size=apBufferedRegions.size();
  Integer level=null;
  for (int i=0; i < size; i++) {
    p=apBufferedRegions.get(i);
    p.process();
    itemNameList=p.getItemNameList();
    if (StringUtils.isNotEmpty(itemNameList)) {
      level=p.getLevel();
      APBufferedRegion nextP=null;
      if (i < size) {
        nextP=getNextPInferiorToLevel(i + 1,level);
      }
      if (nextP != null) {
        nextP.addIgnoreStartLoopDirective(itemNameList);
        if (!isIgnoreEndLoopDirective(itemNameList)) {
          nextP.setEndLoopDirective(itemNameList);
          addIgnoreEndLoopDirective(itemNameList);
        }
      }
 else {
        if (!isIgnoreEndLoopDirective(itemNameList)) {
          p.setEndLoopDirective(itemNameList);
          addIgnoreEndLoopDirective(itemNameList);
        }
      }
    }
  }
}
