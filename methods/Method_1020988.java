@Override public void put(String cacheName,Object key,Object value,int liveSeconds){
  if (liveSeconds <= 0) {
    put(cacheName,key,value);
    return;
  }
  Element element=new Element(key,value);
  element.setTimeToLive(liveSeconds);
  getOrAddCache(cacheName).put(element);
}
