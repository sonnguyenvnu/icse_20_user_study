public char peek(int ofset){
  if (ofset == 0) {
    return (char)cursor;
  }
  if (cache.size() > ofset - 1) {
    return (char)cache.get(ofset - 1).intValue();
  }
  int t=0;
  for (int i=0; i < ofset - cache.size(); i++) {
    try {
      t=reader.read();
      cache.add(t);
    }
 catch (    IOException e) {
      log.debug("read error",e);
    }
  }
  return (char)t;
}
