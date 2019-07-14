public Set<String> keySet(){
  return new Set<String>(){
    @Override public int size(){
      return MutableDoubleArrayTrieInteger.this.size;
    }
    @Override public boolean isEmpty(){
      return MutableDoubleArrayTrieInteger.this.isEmpty();
    }
    @Override public boolean contains(    Object o){
      return MutableDoubleArrayTrieInteger.this.containsKey((String)o);
    }
    @Override public Iterator<String> iterator(){
      return new Iterator<String>(){
        @Override public void remove(){
          throw new UnsupportedOperationException();
        }
        @Override public boolean hasNext(){
          return iterator.hasNext();
        }
        @Override public String next(){
          return iterator.next().key;
        }
      }
;
    }
    @Override public Object[] toArray(){
      throw new UnsupportedOperationException();
    }
    @Override public <T>T[] toArray(    T[] a){
      throw new UnsupportedOperationException();
    }
    @Override public boolean add(    String s){
      throw new UnsupportedOperationException();
    }
    @Override public boolean remove(    Object o){
      throw new UnsupportedOperationException();
    }
    @Override public boolean containsAll(    Collection<?> c){
      throw new UnsupportedOperationException();
    }
    @Override public boolean addAll(    Collection<? extends String> c){
      throw new UnsupportedOperationException();
    }
    @Override public boolean retainAll(    Collection<?> c){
      throw new UnsupportedOperationException();
    }
    @Override public boolean removeAll(    Collection<?> c){
      throw new UnsupportedOperationException();
    }
    @Override public void clear(){
      throw new UnsupportedOperationException();
    }
  }
;
}
