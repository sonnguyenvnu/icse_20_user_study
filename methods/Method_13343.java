public void indexesChanged(Collection<Future<Indexes>> collectionOfFutureIndexes){
  this.collectionOfFutureIndexes=collectionOfFutureIndexes;
  boolean refresh=false;
  for (  Map.Entry<Integer,HyperlinkData> entry : hyperlinks.entrySet()) {
    TypeHyperlinkData data=(TypeHyperlinkData)entry.getValue();
    boolean enabled;
    if (data instanceof PathHyperlinkData) {
      PathHyperlinkData d=(PathHyperlinkData)data;
      enabled=searchEntry(this.entry.getContainer().getRoot(),d.path) != null;
    }
 else {
      String internalTypeName=data.internalTypeName;
      enabled=IndexesUtil.containsInternalTypeName(collectionOfFutureIndexes,internalTypeName);
    }
    if (data.enabled != enabled) {
      data.enabled=enabled;
      refresh=true;
    }
  }
  if (refresh) {
    textArea.repaint();
  }
}
