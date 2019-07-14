/** 
 * Validates pointer members that should not be  {@code NULL}.
 * @param struct the struct to validate
 */
public static void validate(long struct){
  int mNumFaces=nmNumFaces(struct);
  check(memGetAddress(struct + AIMesh.MVERTICES));
  long mFaces=memGetAddress(struct + AIMesh.MFACES);
  check(mFaces);
  AIFace.validate(mFaces,mNumFaces);
  if (nmNumBones(struct) != 0) {
    check(memGetAddress(struct + AIMesh.MBONES));
  }
  if (nmNumAnimMeshes(struct) != 0) {
    check(memGetAddress(struct + AIMesh.MANIMMESHES));
  }
}
