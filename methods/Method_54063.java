/** 
 * Validates pointer members that should not be  {@code NULL}.
 * @param struct the struct to validate
 */
public static void validate(long struct){
  if (nmNumPositionKeys(struct) != 0) {
    check(memGetAddress(struct + AINodeAnim.MPOSITIONKEYS));
  }
  if (nmNumRotationKeys(struct) != 0) {
    check(memGetAddress(struct + AINodeAnim.MROTATIONKEYS));
  }
  if (nmNumScalingKeys(struct) != 0) {
    check(memGetAddress(struct + AINodeAnim.MSCALINGKEYS));
  }
}
