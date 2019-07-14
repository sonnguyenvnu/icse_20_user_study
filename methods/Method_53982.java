/** 
 * Returns a  {@link AIFace.Buffer} view of the struct array pointed to by the {@code mFaces} field. 
 */
@NativeType("struct aiFace *") public AIFace.Buffer mFaces(){
  return nmFaces(address());
}
