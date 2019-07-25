/** 
 * {@inheritDoc}
 */
@Override public VType remove(KType key){
  final int mask=this.mask;
  if (Intrinsics.<KType>isEmpty(key)) {
    hasEmptyKey=false;
    VType previousValue=Intrinsics.<VType>cast(values[mask + 1]);
    values[mask + 1]=Intrinsics.<VType>empty();
    return previousValue;
  }
 else {
    final KType[] keys=Intrinsics.<KType[]>cast(this.keys);
    int slot=hashKey(key) & mask;
    KType existing;
    while (!Intrinsics.<KType>isEmpty(existing=keys[slot])) {
      if (Intrinsics.<KType>equals(this,key,existing)) {
        final VType previousValue=Intrinsics.<VType>cast(values[slot]);
        shiftConflictingKeys(slot);
        return previousValue;
      }
      slot=(slot + 1) & mask;
    }
    return Intrinsics.<VType>empty();
  }
}
