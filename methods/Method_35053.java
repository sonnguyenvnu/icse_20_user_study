public static <Key,Parsed>Cache<Key,Observable<Parsed>> createRoomInflighter(MemoryPolicy memoryPolicy){
  return createBaseInFlighter(memoryPolicy);
}
