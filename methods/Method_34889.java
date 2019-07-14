/** 
 * Returns the docid of an already seen url.
 * @param url the URL for which the docid is returned.
 * @return the docid of the url if it is seen before. Otherwise -1 is returned.
 */
public int getDocId(String url){
synchronized (mutex) {
    OperationStatus result=null;
    DatabaseEntry value=new DatabaseEntry();
    try {
      DatabaseEntry key=new DatabaseEntry(url.getBytes());
      result=docIDsDB.get(null,key,value,null);
    }
 catch (    RuntimeException e) {
      if (config.isHaltOnError()) {
        throw e;
      }
 else {
        logger.error("Exception thrown while getting DocID",e);
        return -1;
      }
    }
    if ((result == OperationStatus.SUCCESS) && (value.getData().length > 0)) {
      return Util.byteArray2Int(value.getData());
    }
    return -1;
  }
}
