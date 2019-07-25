/** 
 * @param map a Java map
 * @return a Bifurcan {@code IMap} which does not support {@code nth()} or {@code indexOf()}
 */
public static <K,V>IMap<K,V> from(java.util.Map<K,V> map){
  return new IMap<K,V>(){
    @Override public V get(    K key,    V defaultValue){
      V val=map.get(key);
      if (val == null) {
        return map.containsKey(key) ? null : defaultValue;
      }
      return val;
    }
    @Override public boolean contains(    K key){
      return map.containsKey(key);
    }
    @Override public long indexOf(    K key){
      throw new UnsupportedOperationException();
    }
    @Override public long size(){
      return map.size();
    }
    @Override public IEntry<K,V> nth(    long idx){
      throw new UnsupportedOperationException();
    }
    @Override public Iterator<IEntry<K,V>> iterator(){
      return map.entrySet().stream().map(e -> (IEntry<K,V>)new Maps.Entry<>(e.getKey(),e.getValue())).iterator();
    }
    @Override public ToIntFunction<K> keyHash(){
      return Maps.DEFAULT_HASH_CODE;
    }
    @Override public BiPredicate<K,K> keyEquality(){
      return Maps.DEFAULT_EQUALS;
    }
    @Override public int hashCode(){
      return (int)Maps.hash(this);
    }
    @Override public boolean equals(    Object obj){
      if (obj instanceof IMap) {
        return Maps.equals(this,(IMap<K,V>)obj);
      }
      return false;
    }
    @Override public IMap<K,V> clone(){
      return this;
    }
    @Override public String toString(){
      return Maps.toString(this);
    }
  }
;
}
