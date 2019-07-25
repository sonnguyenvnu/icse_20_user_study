public static <T>Iterator<T> concat(final Iterator<? extends T> it1,final Iterator<? extends T> it2){
  return new Iterator<T>(){
    @Override public boolean hasNext(){
      if (myFirstActive) {
        if (it1.hasNext()) {
          return true;
        }
 else {
          myFirstActive=false;
          return it2.hasNext();
        }
      }
 else {
        return it2.hasNext();
      }
    }
    @Override public T next(){
      if (myFirstActive) {
        if (it1.hasNext()) {
          return it1.next();
        }
 else {
          myFirstActive=false;
          return it2.next();
        }
      }
 else {
        return it2.next();
      }
    }
    @Override public void remove(){
      throw new UnsupportedOperationException();
    }
  }
;
}
