/** 
 * Sets the address of the specified  {@link AIVector3D.Buffer} to the {@code mTangents} field. 
 */
public AIMesh mTangents(@Nullable @NativeType("struct aiVector3D *") AIVector3D.Buffer value){
  nmTangents(address(),value);
  return this;
}
