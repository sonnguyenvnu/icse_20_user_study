public InMemoryClientDetailsServiceBuilder inMemory() throws Exception {
  InMemoryClientDetailsServiceBuilder next=getBuilder().inMemory();
  setBuilder(next);
  return next;
}
