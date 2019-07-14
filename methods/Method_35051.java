static <Key,Parsed>Cache<Key,Single<Parsed>> createInflighter(MemoryPolicy memoryPolicy){
  return createBaseInFlighter(memoryPolicy);
}
