/** 
 * @param nodesFrom The source path represented in an array of PathDataNode
 * @param nodesTo The target path represented in an array of PathDataNode
 * @return whether the <code>nodesFrom</code> can morph into <code>nodesTo</code>
 */
public static boolean canMorph(@Nullable PathDataNode[] nodesFrom,@Nullable PathDataNode[] nodesTo){
  if (nodesFrom == null || nodesTo == null) {
    return false;
  }
  if (nodesFrom.length != nodesTo.length) {
    return false;
  }
  for (int i=0; i < nodesFrom.length; i++) {
    if (nodesFrom[i].mType != nodesTo[i].mType || nodesFrom[i].mParams.length != nodesTo[i].mParams.length) {
      return false;
    }
  }
  return true;
}
