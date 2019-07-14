/** 
 * ??Map?? ??????
 */
private Map<Character,DictSegment> getChildrenMap(){
synchronized (this) {
    if (this.childrenMap == null) {
      this.childrenMap=new ConcurrentHashMap<Character,DictSegment>(ARRAY_LENGTH_LIMIT * 2,0.8f);
    }
  }
  return this.childrenMap;
}
