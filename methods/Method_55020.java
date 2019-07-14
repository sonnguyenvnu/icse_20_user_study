/** 
 * Validates pointer members that should not be  {@code NULL}.
 * @param struct the struct to validate
 */
public static void validate(long struct){
  check(memGetAddress(struct + JNINativeMethod.NAME));
  check(memGetAddress(struct + JNINativeMethod.SIGNATURE));
  check(memGetAddress(struct + JNINativeMethod.FNPTR));
}
