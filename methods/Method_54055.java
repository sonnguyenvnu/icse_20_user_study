/** 
 * Returns a  {@link AIString} view of the {@code mNodeName} field. 
 */
@NativeType("struct aiString") public AIString mNodeName(){
  return nmNodeName(address());
}
