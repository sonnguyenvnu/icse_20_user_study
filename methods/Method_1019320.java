/** 
 * Rehash from old buffers to new buffers. 
 */
protected void rehash(KType[] fromKeys,VType[] fromValues){
  assert fromKeys.length == fromValues.length && HashContainers.checkPowerOfTwo(fromKeys.length - 1);
  final KType[] keys=Intrinsics.<KType[]>cast(this.keys);
  final VType[] values=Intrinsics.<VType[]>cast(this.values);
  final int mask=this.mask;
  KType existing;
  int from=fromKeys.length - 1;
  keys[keys.length - 1]=fromKeys[from];
  values[values.length - 1]=fromValues[from];
  while (--from >= 0) {
    if (!Intrinsics.<KType>isEmpty(existing=fromKeys[from])) {
      int slot=hashKey(existing) & mask;
      while (!Intrinsics.<KType>isEmpty(keys[slot])) {
        slot=(slot + 1) & mask;
      }
      keys[slot]=existing;
      values[slot]=fromValues[from];
    }
  }
}
