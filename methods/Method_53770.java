/** 
 * Validates pointer members that should not be  {@code NULL}.
 * @param struct the struct to validate
 */
public static void validate(long struct){
  if (nmNumChannels(struct) != 0) {
    check(memGetAddress(struct + AIAnimation.MCHANNELS));
  }
  if (nmNumMeshChannels(struct) != 0) {
    check(memGetAddress(struct + AIAnimation.MMESHCHANNELS));
  }
  if (nmNumMorphMeshChannels(struct) != 0) {
    check(memGetAddress(struct + AIAnimation.MMORPHMESHCHANNELS));
  }
}
