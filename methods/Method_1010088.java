/** 
 * Replacement for <code>getNodeId().equals(nodePointer.getNodeId().toString())</code>.
 * @param nodeId tolerates null
 * @return <code>true</code> iff is the same as the one associated with this position.
 */
public boolean matches(@Nullable SNodeId nodeId){
  return Objects.equals(myNodeId,nodeId == null ? null : nodeId.toString());
}
