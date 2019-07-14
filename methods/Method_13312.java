public void indexesChanged(Collection<Future<Indexes>> collectionOfFutureIndexes){
  this.collectionOfFutureIndexes=collectionOfFutureIndexes;
  boolean refresh=false;
  String text=getText();
  for (  Map.Entry<Integer,HyperlinkData> entry : hyperlinks.entrySet()) {
    LogHyperlinkData entryData=(LogHyperlinkData)entry.getValue();
    String typeAndMethodNames=text.substring(entryData.startPosition,entryData.endPosition);
    int lastDotIndex=typeAndMethodNames.lastIndexOf('.');
    String internalTypeName=typeAndMethodNames.substring(0,lastDotIndex).replace('.','/');
    boolean enabled=IndexesUtil.containsInternalTypeName(collectionOfFutureIndexes,internalTypeName);
    if (entryData.enabled != enabled) {
      entryData.enabled=enabled;
      refresh=true;
    }
  }
  if (refresh) {
    textArea.repaint();
  }
}
