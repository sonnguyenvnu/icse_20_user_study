public void addUrlAndDocId(String url,int docId){
synchronized (mutex) {
    if (docId <= lastDocID) {
      throw new IllegalArgumentException("Requested doc id: " + docId + " is not larger than: " + lastDocID);
    }
    int prevDocid=getDocId(url);
    if (prevDocid > 0) {
      if (prevDocid == docId) {
        return;
      }
      throw new IllegalArgumentException("Doc id: " + prevDocid + " is already assigned to URL: " + url);
    }
    docIDsDB.put(null,new DatabaseEntry(url.getBytes()),new DatabaseEntry(Util.int2ByteArray(docId)));
    lastDocID=docId;
  }
}
