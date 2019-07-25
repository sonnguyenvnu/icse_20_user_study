/** 
 * Negate the given mask.
 * @param mask the mask
 * @return a new mask
 */
public static Mask negate(final Mask mask){
  if (mask instanceof AlwaysTrue) {
    return ALWAYS_FALSE;
  }
 else   if (mask instanceof AlwaysFalse) {
    return ALWAYS_TRUE;
  }
  checkNotNull(mask);
  return new AbstractMask(){
    @Override public boolean test(    BlockVector3 vector){
      return !mask.test(vector);
    }
    @Nullable @Override public Mask2D toMask2D(){
      Mask2D mask2d=mask.toMask2D();
      if (mask2d != null) {
        return negate(mask2d);
      }
 else {
        return null;
      }
    }
  }
;
}
