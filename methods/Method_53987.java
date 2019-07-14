/** 
 * Sets the address of the specified  {@link PointerBuffer} to the {@code mBones} field. 
 */
public AIMesh mBones(@Nullable @NativeType("struct aiBone **") PointerBuffer value){
  nmBones(address(),value);
  return this;
}
