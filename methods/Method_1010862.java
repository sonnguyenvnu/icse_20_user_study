protected final Iterable<MPSPsiNodeBase> children(){
  return new Iterable<MPSPsiNodeBase>(){
    @Override public Iterator<MPSPsiNodeBase> iterator(){
      return new Iterator<MPSPsiNodeBase>(){
        @Override public boolean hasNext(){
          return (node == null && children.first() != null) || (node != null && node != children.last());
        }
        @Override public MPSPsiNodeBase next(){
          if (!hasNext())           throw new NoSuchElementException();
          if (node == null) {
            return (node=children.first());
          }
 else {
            return (node=children.next(node));
          }
        }
        @Override public void remove(){
          throw new UnsupportedOperationException();
        }
      }
;
    }
  }
;
}
