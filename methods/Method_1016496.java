public static <A>CloneableIterator<A> cascade(final Collection<CloneableIterator<A>> iterators,final Order<A> c,final Method merger,final boolean up){
  if (iterators == null || iterators.isEmpty())   return new CloneableIterator<A>(){
    @Override public boolean hasNext(){
      return false;
    }
    @Override public A next(){
      return null;
    }
    @Override public void remove(){
    }
    @Override public CloneableIterator<A> clone(    Object modifier){
      return this;
    }
    @Override public void close(){
    }
  }
;
  return cascade(iterators.iterator(),c,merger,up);
}
