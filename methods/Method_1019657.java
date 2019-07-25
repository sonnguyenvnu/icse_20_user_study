public void process(){
  Collection<BufferedElement> toRemove=new ArrayList<BufferedElement>();
  boolean remove=false;
  boolean fieldNameSetted=false;
  String fieldName=null;
  boolean rReseted=false;
  for (int i=0; i < rBufferedRegions.size(); i++) {
    RBufferedRegion rBufferedRegion=rBufferedRegions.get(i);
    if (BEGIN.equals(rBufferedRegion.getFldCharType())) {
      int nextIndexAfterBegin=i + 1;
      RBufferedRegion nextR=rBufferedRegions.get(nextIndexAfterBegin);
      rReseted=nextR.isReseted();
      if (rReseted) {
        toRemove.add(rBufferedRegion);
        toRemove.add(nextR);
        remove=true;
      }
 else {
        List<RBufferedRegion> rMerged=new ArrayList<RBufferedRegion>();
        StringBuilder mergedInstrText=new StringBuilder();
        for (int j=nextIndexAfterBegin; j < rBufferedRegions.size(); j++) {
          RBufferedRegion r=rBufferedRegions.get(j);
          if (r.hasInstrText()) {
            rMerged.add(r);
            mergedInstrText.append(r.getOriginalInstrText());
          }
 else {
            break;
          }
        }
        if (rMerged.size() > 0) {
          RBufferedRegion firstR=rMerged.get(0);
          firstR.setInstrText(mergedInstrText.toString(),firstR.getFieldAsTextStyling());
          fieldName=firstR.getFieldName();
          if (fieldName != null) {
            if (document.processScriptBeforeAfter(firstR)) {
              rReseted=true;
            }
            for (            RBufferedRegion r : rMerged) {
              toRemove.add(r);
              i++;
            }
            toRemove.add(rBufferedRegion);
            remove=true;
          }
        }
      }
    }
 else     if (SEPARATE.equals(rBufferedRegion.getFldCharType()) && remove) {
      toRemove.add(rBufferedRegion);
    }
 else     if (END.equals(rBufferedRegion.getFldCharType()) && remove) {
      toRemove.add(rBufferedRegion);
      remove=false;
      fieldName=null;
      fieldNameSetted=false;
      rReseted=false;
    }
 else     if (fieldName != null || rReseted) {
      if (!fieldNameSetted && !rReseted) {
        rBufferedRegion.setTContent(0,fieldName);
        fieldNameSetted=true;
        containsField=true;
      }
 else {
        if (!rBufferedRegion.isContainsNote()) {
          toRemove.add(rBufferedRegion);
        }
      }
    }
  }
  rBufferedRegions.removeAll(toRemove);
  super.removeAll(toRemove);
}
