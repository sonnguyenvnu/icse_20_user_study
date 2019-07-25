public static Scope unique(Scope scope){
  return new DelegatingScope(scope){
    @Override public Iterable<SNode> getAvailableElements(    @Nullable String prefix){
      return Sequence.fromIterable(super.getAvailableElements(prefix)).distinct();
    }
  }
;
}
