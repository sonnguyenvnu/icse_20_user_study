public static void b3SetCollisionFilterPair(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueIdA,int bodyUniqueIdB,int linkIndexA,int linkIndexB,@NativeType("int") boolean enableCollision){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3SetCollisionFilterPair(commandHandle,bodyUniqueIdA,bodyUniqueIdB,linkIndexA,linkIndexB,enableCollision ? 1 : 0);
}
