/** 
 * Returns whether two users are different where equality is determined by matching IDs.
 */
public static boolean userHasChanged(final @Nullable User u1,final @Nullable User u2){
  if (ObjectUtils.isNull(u1) && ObjectUtils.isNull(u2)) {
    return false;
  }
 else   if (ObjectUtils.isNotNull(u1) && ObjectUtils.isNotNull(u2)) {
    return u1.id() != u2.id();
  }
  return true;
}
