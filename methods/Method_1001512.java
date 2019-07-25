public static <O>Navigator<O> reverse(final Navigator<O> orig){
  return new Navigator<O>(){
    public O next(){
      return orig.previous();
    }
    public O previous(){
      return orig.next();
    }
    public O get(){
      return orig.get();
    }
    public void set(    O data){
      orig.set(data);
    }
    public void insertBefore(    O data){
      throw new UnsupportedOperationException();
    }
    public void insertAfter(    O data){
      throw new UnsupportedOperationException();
    }
  }
;
}
