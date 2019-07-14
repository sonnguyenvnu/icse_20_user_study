/** 
 * Unsafe version of  {@link #mChildren() mChildren}. 
 */
@Nullable public static PointerBuffer nmChildren(long struct){
  return memPointerBufferSafe(memGetAddress(struct + AINode.MCHILDREN),nmNumChildren(struct));
}
