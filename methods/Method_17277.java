@Override public ConcurrentMap<K,V> asMap(){
  return new ForwardingConcurrentMap<K,V>(){
    @Override public boolean containsKey(    Object key){
      return (key != null) && delegate().containsKey(key);
    }
    @Override public boolean containsValue(    Object value){
      return (value != null) && delegate().containsValue(value);
    }
    @Override public void replaceAll(    BiFunction<? super K,? super V,? extends V> function){
      delegate().replaceAll(function);
    }
    @Override public V computeIfAbsent(    K key,    Function<? super K,? extends V> mappingFunction){
      return delegate().computeIfAbsent(key,mappingFunction);
    }
    @Override public V computeIfPresent(    K key,    BiFunction<? super K,? super V,? extends V> remappingFunction){
      return delegate().computeIfPresent(key,remappingFunction);
    }
    @Override public V compute(    K key,    BiFunction<? super K,? super V,? extends V> remappingFunction){
      return delegate().compute(key,remappingFunction);
    }
    @Override public V merge(    K key,    V value,    BiFunction<? super V,? super V,? extends V> remappingFunction){
      return delegate().merge(key,value,remappingFunction);
    }
    @Override public Set<K> keySet(){
      return new ForwardingSet<K>(){
        @Override public boolean removeIf(        Predicate<? super K> filter){
          return delegate().removeIf(filter);
        }
        @Override public boolean remove(        Object o){
          return (o != null) && delegate().remove(o);
        }
        @Override protected Set<K> delegate(){
          return cache.asMap().keySet();
        }
      }
;
    }
    @Override public Collection<V> values(){
      return new ForwardingCollection<V>(){
        @Override public boolean removeIf(        Predicate<? super V> filter){
          return delegate().removeIf(filter);
        }
        @Override public boolean remove(        Object o){
          return (o != null) && delegate().remove(o);
        }
        @Override protected Collection<V> delegate(){
          return cache.asMap().values();
        }
      }
;
    }
    @Override public Set<Entry<K,V>> entrySet(){
      return new ForwardingSet<Entry<K,V>>(){
        @Override public boolean add(        Entry<K,V> entry){
          throw new UnsupportedOperationException();
        }
        @Override public boolean addAll(        Collection<? extends Entry<K,V>> entry){
          throw new UnsupportedOperationException();
        }
        @Override public boolean removeIf(        Predicate<? super Entry<K,V>> filter){
          return delegate().removeIf(filter);
        }
        @Override protected Set<Entry<K,V>> delegate(){
          return cache.asMap().entrySet();
        }
      }
;
    }
    @Override protected ConcurrentMap<K,V> delegate(){
      return cache.asMap();
    }
  }
;
}
