public static <Key,Parsed>Cache<Key,Observable<Parsed>> createRoomCache(MemoryPolicy memoryPolicy){
  return createBaseCache(memoryPolicy);
}
