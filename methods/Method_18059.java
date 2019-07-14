/** 
 * @return This component's testKey or null if none is set.
 */
@Nullable public String getTestKey(){
  return isLayoutNode() ? mNode.getTestKey() : null;
}
