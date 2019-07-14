/** 
 * Returns a  {@link AIString} view of the {@code mFilename} field. 
 */
@NativeType("struct aiString") public AIString mFilename(){
  return nmFilename(address());
}
