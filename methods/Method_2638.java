@Override public Set<String> keySet(){
  return new Set<String>(){
    @Override public int size(){
      return trie.size();
    }
    @Override public boolean isEmpty(){
      return trie.isEmpty();
    }
    @Override public boolean contains(    Object o){
      throw new UnsupportedOperationException();
    }
    @Override public Iterator<String> iterator(){
      return new Iterator<String>(){
        @Override public boolean hasNext(){
          return iterator.hasNext();
        }
        @Override public String next(){
          return iterator.next().key();
        }
        @Override public void remove(){
          throw new UnsupportedOperationException();
        }
      }
;
    }
    @Override public Object[] toArray(){
      return values.toArray();
    }
    @Override public <T>T[] toArray(    T[] a){
      return values.toArray(a);
    }
    @Override public boolean add(    String s){
      throw new UnsupportedOperationException();
    }
    @Override public boolean remove(    Object o){
      return trie.remove((String)o) != -1;
    }
    @Override public boolean containsAll(    Collection<?> c){
      for (      Object o : c) {
        if (!trie.containsKey((String)o))         return false;
      }
      return true;
    }
    @Override public boolean addAll(    Collection<? extends String> c){
      throw new UnsupportedOperationException();
    }
    @Override public boolean retainAll(    Collection<?> c){
      throw new UnsupportedOperationException();
    }
    @Override public boolean removeAll(    Collection<?> c){
      boolean changed=false;
      for (      Object o : c) {
        if (!changed)         changed=MutableDoubleArrayTrie.this.remove(o) != null;
      }
      return changed;
    }
    @Override public void clear(){
      MutableDoubleArrayTrie.this.clear();
    }
  }
;
}
