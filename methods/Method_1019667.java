public void process(){
  Collection<BufferedElement> toRemove=new ArrayList<BufferedElement>();
  int size=arBufferedRegions.size();
  String s=null;
  StringBuilder fullContent=new StringBuilder();
  ARBufferedRegion currentAR=null;
  ARBufferedRegion lastAR=null;
  boolean hasField=false;
  boolean fieldParsing=false;
  for (int i=0; i < size; i++) {
    currentAR=arBufferedRegions.get(i);
    s=currentAR.getTContent();
    hasField=s != null && s.indexOf("$") != -1;
    if (fieldParsing) {
      fieldParsing=(s == null || s.length() == 0 || Character.isWhitespace(s.charAt(0)));
      if (!fieldParsing) {
        if (hasField) {
          update(toRemove,fullContent,lastAR);
          fieldParsing=true;
          fullContent.append(s);
          toRemove.add(currentAR);
        }
 else {
          fullContent.append(s);
          update(toRemove,fullContent,currentAR);
        }
      }
 else {
        fullContent.append(s);
        toRemove.add(currentAR);
      }
    }
 else {
      if (hasField) {
        fieldParsing=true;
        fullContent.append(s);
        toRemove.add(currentAR);
      }
 else {
      }
    }
    lastAR=currentAR;
  }
  update(toRemove,fullContent,lastAR);
  super.removeAll(toRemove);
}
