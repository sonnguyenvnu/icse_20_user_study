public void indexesChanged(Collection<Future<Indexes>> collectionOfFutureIndexes){
  this.collectionOfFutureIndexes=collectionOfFutureIndexes;
  boolean refresh=false;
  String text=getText();
  for (  Map.Entry<Integer,HyperlinkData> entry : hyperlinks.entrySet()) {
    ManifestHyperlinkData entryData=(ManifestHyperlinkData)entry.getValue();
    String textLink=getValue(text,entryData.startPosition,entryData.endPosition);
    String internalTypeName=textLink.replace('.','/');
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
