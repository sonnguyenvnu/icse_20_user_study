/** 
 * Validates pointer members that should not be  {@code NULL}.
 * @param struct the struct to validate
 */
public static void validate(long struct){
  int mNumKeys=nmNumKeys(struct);
  long mKeys=memGetAddress(struct + AIMeshMorphAnim.MKEYS);
  check(mKeys);
  AIMeshMorphKey.validate(mKeys,mNumKeys);
}
