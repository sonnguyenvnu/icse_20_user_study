@Override public void afterStubRemoved(StubMapping stub){
  cache.invalidateAll();
}
