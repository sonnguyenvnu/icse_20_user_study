public void indexesChanged(Collection<Future<Indexes>> collectionOfFutureIndexes){
  this.collectionOfFutureIndexes=collectionOfFutureIndexes;
  boolean refresh=false;
  for (  Map.Entry<Integer,HyperlinkData> entry : hyperlinks.entrySet()) {
    TypeHyperlinkData data=(TypeHyperlinkData)entry.getValue();
    String internalTypeName=data.internalTypeName;
    boolean enabled=IndexesUtil.containsInternalTypeName(collectionOfFutureIndexes,internalTypeName);
    if (data.enabled != enabled) {
      data.enabled=enabled;
      refresh=true;
    }
  }
  if (refresh) {
    textArea.repaint();
  }
}
