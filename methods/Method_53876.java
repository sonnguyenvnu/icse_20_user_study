/** 
 * Returns a  {@link AIColor3D} view of the {@code mColorSpecular} field. 
 */
@NativeType("struct aiColor3D") public AIColor3D mColorSpecular(){
  return nmColorSpecular(address());
}
