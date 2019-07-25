/** 
 * Looks up a tree type. May return null if a tree type by that name is not found.
 * @param type the tree type
 * @return a tree type or null
 */
@Nullable public static TreeType lookup(String type){
  return TreeType.lookup(type);
}
