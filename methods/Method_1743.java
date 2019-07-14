@Override public Inserter insert(String resourceId,Object debugInfo) throws IOException {
  FileInfo info=new FileInfo(FileType.TEMP,resourceId);
  File parent=getSubdirectory(info.resourceId);
  if (!parent.exists()) {
    mkdirs(parent,"insert");
  }
  try {
    File file=info.createTempFile(parent);
    return new InserterImpl(resourceId,file);
  }
 catch (  IOException ioe) {
    mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_TEMPFILE,TAG,"insert",ioe);
    throw ioe;
  }
}
