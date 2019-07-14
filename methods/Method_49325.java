@Override public Set<String> keySet(){
  return new Set<String>(){
    @Override public int size(){
      return keys.length;
    }
    @Override public boolean isEmpty(){
      return false;
    }
    @Override public boolean contains(    Object o){
      return indexOf(keys,o) >= 0;
    }
    @Override public Iterator<String> iterator(){
      return new Iterator<String>(){
        @Override public boolean hasNext(){
          return currentPos < keys.length - 1;
        }
        @Override public String next(){
          if (!hasNext())           throw new NoSuchElementException();
          currentPos++;
          return keys[currentPos];
        }
        @Override public void remove(){
          throw new UnsupportedOperationException("This map is immutable");
        }
      }
;
    }
    @Override public Object[] toArray(){
      throw new UnsupportedOperationException();
    }
    @Override public <T>T[] toArray(    T[] ts){
      throw new UnsupportedOperationException();
    }
    @Override public boolean add(    String s){
      throw new UnsupportedOperationException("This map is immutable");
    }
    @Override public boolean remove(    Object o){
      throw new UnsupportedOperationException("This map is immutable");
    }
    @Override public boolean containsAll(    Collection<?> objects){
      for (      Object o : objects)       if (!contains(o))       return false;
      return true;
    }
    @Override public boolean addAll(    Collection<? extends String> strings){
      throw new UnsupportedOperationException("This map is immutable");
    }
    @Override public boolean retainAll(    Collection<?> objects){
      throw new UnsupportedOperationException("This map is immutable");
    }
    @Override public boolean removeAll(    Collection<?> objects){
      throw new UnsupportedOperationException("This map is immutable");
    }
    @Override public void clear(){
      throw new UnsupportedOperationException("This map is immutable");
    }
  }
;
}
