public boolean delete(boolean synchronous){
  if (!exists())   return false;
  if (isFile()) {
    fs.remove(getPath(),synchronous);
    cache.remove(getPath(),synchronous);
    return true;
  }
  if (isDirectory()) {
    File[] files=listFiles();
    if (files != null && files.length > 0)     return false;
    fs.remove(getPath(),synchronous);
    cache.remove(getPath(),synchronous);
  }
  return true;
}
