public void inc(String key,long x){
  Long old=getInt(key);
  if (old == null) {
    contents.put(key,1);
  }
 else {
    contents.put(key,old + x);
  }
}
