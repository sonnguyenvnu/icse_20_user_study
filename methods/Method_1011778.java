@Override public void run(){
  myLoading=true;
  try {
    RootHistoryModel.NodeSet ns=new RootHistoryModel.NodeSet(myRoot);
    VcsFileRevision prevRevision=null;
    RootHistoryModel.ContentRange prevContentRange=null;
    for (    VcsFileRevision rev : myInitialRevisions) {
      myProcessedRevisions++;
      try {
        byte[] revContent=rev.loadContent();
        List<LineContent> lineToContentMap=VCSPersistenceSupport.getLineToContentMap(revContent);
        if (lineToContentMap == null) {
          continue;
        }
        RootHistoryModel.ContentRange contentRange=buildRanges(ns,lineToContentMap);
        if (prevRevision != null && contentRange.changedAgainst(prevContentRange)) {
synchronized (myFilteredRevisions) {
            myFilteredRevisions.add(prevRevision);
          }
        }
        prevContentRange=contentRange;
        prevRevision=rev;
        myOnUpdate.run();
      }
 catch (      Exception ex) {
        if (LOG.isEnabledFor(Level.ERROR)) {
          LOG.error("Error processing revision " + rev,ex);
        }
      }
    }
  }
  finally {
    myLoading=false;
  }
  myOnUpdate.run();
}
