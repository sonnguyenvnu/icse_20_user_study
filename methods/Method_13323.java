public void indexesChanged(Collection<Future<Indexes>> collectionOfFutureIndexes){
  this.collectionOfFutureIndexes=collectionOfFutureIndexes;
  boolean refresh=false;
  for (  Map.Entry<Integer,HyperlinkData> entry : hyperlinks.entrySet()) {
    TypeHyperlinkData entryData=(TypeHyperlinkData)entry.getValue();
    String internalTypeName=entryData.internalTypeName;
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
