/** 
 * Rehash from old buffers to new buffers. 
 */
protected void rehash(KType[] fromKeys){
  assert HashContainers.checkPowerOfTwo(fromKeys.length - 1);
  final KType[] keys=Intrinsics.<KType[]>cast(this.keys);
  final int mask=this.mask;
  KType existing;
  for (int i=fromKeys.length - 1; --i >= 0; ) {
    if (!Intrinsics.isEmpty(existing=fromKeys[i])) {
      int slot=hashKey(existing) & mask;
      while (!Intrinsics.isEmpty(keys[slot])) {
        slot=(slot + 1) & mask;
      }
      keys[slot]=existing;
    }
  }
}
