@Override protected boolean cacheExists(){
  return cacheFile.exists() && cacheFile.isFile() && cacheFile.length() > 0;
}
