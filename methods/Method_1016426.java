/** 
 * apply all E/int mappings from an external ScoreMap to this ScoreMap
 */
@Override public void inc(ScoreMap<E> map){
  if (map == null)   return;
  for (  E entry : map) {
    int count=map.get(entry);
    if (count > 0)     this.inc(entry,count);
  }
}
