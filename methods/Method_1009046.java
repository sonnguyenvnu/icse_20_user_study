public Condition repeat(String xpathBase,int index,Map<String,Condition> conditionsMap,Map<String,org.opendope.xpaths.Xpaths.Xpath> xpathsMap){
  org.opendope.xpaths.Xpaths.Xpath xpathObj=xpathsMap.get(id);
  String thisXPath=xpathObj.getDataBinding().getXpath();
  int xpathBaseIdx=thisXPath.indexOf(xpathBase);
  if (xpathBaseIdx < 0) {
    return null;
  }
  if (thisXPath.trim().startsWith("count")) {
    int pos=xpathBaseIdx + xpathBase.length();
    String tail=thisXPath.substring(pos);
    log.debug("the tail: " + tail);
    if (tail.contains("oda:repeat")) {
      log.debug("deeper repeats in count");
    }
 else {
      if (tail.contains("/")) {
        log.debug("deeper bits in count");
      }
 else       if (tail.startsWith("[")) {
        log.debug("index needs enhancement");
      }
 else       if (tail.startsWith(")")) {
        log.debug("retaining (repeat count): " + thisXPath);
        return null;
      }
 else {
        log.info("fallback, enhance: " + thisXPath);
      }
    }
  }
  final String newPath=enhanceXPath(xpathBase,index + 1,thisXPath);
  if (log.isDebugEnabled()) {
    if (thisXPath.equals(newPath)) {
      log.debug("xpath base " + xpathBase + " enhanced NO CHANGE to " + newPath);
    }
 else {
      log.debug("xpath " + thisXPath + " enhanced to " + newPath + " using xpath base " + xpathBase);
    }
  }
  org.opendope.xpaths.Xpaths.Xpath newXPathObj=createNewXPathObject(xpathsMap,newPath,xpathObj,index);
  this.id=newXPathObj.getId();
  return null;
}
