/** 
 * Validates pointer members that should not be  {@code NULL}.
 * @param struct the struct to validate
 */
public static void validate(long struct){
  check(memGetAddress(struct + AIBone.MWEIGHTS));
}
