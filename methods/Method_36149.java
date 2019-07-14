@Override public Optional<StubMapping> get(final UUID id){
  return tryFind(mappings,new Predicate<StubMapping>(){
    @Override public boolean apply(    StubMapping input){
      return input.getUuid().equals(id);
    }
  }
);
}
