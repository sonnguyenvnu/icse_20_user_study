private static int selectSlot(int i){
  return i & (ARENA_SIZE - 1);
}
