/** 
 * Returns a  {@link AIString} view of the {@code mName} field. 
 */
@NativeType("struct aiString") public AIString mName(){
  return nmName(address());
}
