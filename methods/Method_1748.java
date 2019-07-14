private boolean query(String resourceId,boolean touch){
  File contentFile=getContentFileFor(resourceId);
  boolean exists=contentFile.exists();
  if (touch && exists) {
    contentFile.setLastModified(mClock.now());
  }
  return exists;
}
