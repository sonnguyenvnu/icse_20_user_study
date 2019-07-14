/** 
 * Validates pointer members that should not be  {@code NULL}.
 * @param struct the struct to validate
 */
public static void validate(long struct){
  long mRootNode=memGetAddress(struct + AIScene.MROOTNODE);
  if (mRootNode != NULL) {
    AINode.validate(mRootNode);
  }
  if (nmNumMeshes(struct) != 0) {
    check(memGetAddress(struct + AIScene.MMESHES));
  }
  if (nmNumMaterials(struct) != 0) {
    check(memGetAddress(struct + AIScene.MMATERIALS));
  }
  if (nmNumAnimations(struct) != 0) {
    check(memGetAddress(struct + AIScene.MANIMATIONS));
  }
  if (nmNumTextures(struct) != 0) {
    check(memGetAddress(struct + AIScene.MTEXTURES));
  }
  if (nmNumLights(struct) != 0) {
    check(memGetAddress(struct + AIScene.MLIGHTS));
  }
  if (nmNumCameras(struct) != 0) {
    check(memGetAddress(struct + AIScene.MCAMERAS));
  }
  long mMetaData=memGetAddress(struct + AIScene.MMETADATA);
  if (mMetaData != NULL) {
    AIMetaData.validate(mMetaData);
  }
}
