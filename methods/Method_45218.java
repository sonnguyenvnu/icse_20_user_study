private Supplier<ImmutableMap<String,String>> emptyMapSupplier(){
  return new Supplier<ImmutableMap<String,String>>(){
    @Override public ImmutableMap<String,String> get(){
      return ImmutableMap.of();
    }
  }
;
}
