@Override public void visit(TextPageCountElement ele){
  if (forcedPageCount != null) {
    createAndAddChunk(forcedPageCount.toString(),null,false);
  }
 else {
    String textContent=ele.getTextContent();
    try {
      int pageCount=Integer.parseInt(textContent);
      if (expectedPageCount == null || expectedPageCount == pageCount) {
        expectedPageCount=pageCount;
      }
 else       if (expectedPageCount != pageCount) {
        expectedPageCount=-1;
      }
    }
 catch (    NumberFormatException e) {
      expectedPageCount=-1;
    }
    textContent=expectedPageCount != null & expectedPageCount >= 0 ? expectedPageCount.toString() : "#";
    createAndAddChunk(textContent,null,false);
  }
}
