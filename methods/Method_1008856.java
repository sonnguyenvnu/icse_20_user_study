private void process(){
  List<Object> content=null;
  List<Object> groupedContent=null;
  SdtFinder sdtFinder=new SdtFinder();
  new TraversalUtil(mainDocument.getContent(),sdtFinder);
  for (  SdtElement sdtEl : sdtFinder.getSdtList()) {
    content=sdtEl.getSdtContent().getContent();
    groupedContent=groupContent(content);
    if (groupedContent != null) {
      content.clear();
      content.addAll(groupedContent);
    }
  }
  TcFinder tcFinder=new TcFinder();
  tcFinder.setTraverseTables(true);
  new TraversalUtil(mainDocument.getContent(),tcFinder);
  for (  Tc tc : tcFinder.tcList) {
    content=tc.getContent();
    groupedContent=groupContent(content);
    if (groupedContent != null) {
      content.clear();
      content.addAll(groupedContent);
    }
  }
  content=mainDocument.getContent();
  groupedContent=groupContent(content);
  if (groupedContent != null) {
    content.clear();
    content.addAll(groupedContent);
  }
}
