/** 
 * An alias for the (preferred)  {@link #removeAll}.
 */
public boolean remove(KType key){
  if (Intrinsics.isEmpty(key)) {
    boolean hadEmptyKey=hasEmptyKey;
    hasEmptyKey=false;
    return hadEmptyKey;
  }
 else {
    final KType[] keys=Intrinsics.<KType[]>cast(this.keys);
    final int mask=this.mask;
    int slot=hashKey(key) & mask;
    KType existing;
    while (!Intrinsics.isEmpty(existing=keys[slot])) {
      if (Intrinsics.equals(this,key,existing)) {
        shiftConflictingKeys(slot);
        return true;
      }
      slot=(slot + 1) & mask;
    }
    return false;
  }
}
