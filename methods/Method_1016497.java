private static <A>CloneableIterator<A> cascade(final Iterator<CloneableIterator<A>> iiterators,final Order<A> c,final Method merger,final boolean up){
  if (iiterators == null || !(iiterators.hasNext()))   return new CloneableIterator<A>(){
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
  final CloneableIterator<A> one=iiterators.next();
  if (!(iiterators.hasNext()))   return one;
  assert merger != null;
  return new MergeIterator<A>(one,cascade(iiterators,c,merger,up),c,merger,up);
}
