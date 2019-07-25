public char poll(){
  char x=(char)cursor;
  try {
    if (cache.isEmpty()) {
      cursor=reader.read();
    }
 else {
      cursor=cache.poll();
    }
  }
 catch (  IOException e) {
    log.debug("read error",e);
  }
  return x;
}
