/** 
 * Decompose a transformation matrix into its rotational, translational and scaling components.
 * @param mat      Matrix to decompose
 * @param scaling  Receives the scaling component
 * @param rotation Receives the rotational component
 * @param position Receives the translational component.
 */
public static void aiDecomposeMatrix(@NativeType("struct aiMatrix4x4 const *") AIMatrix4x4 mat,@NativeType("struct aiVector3D *") AIVector3D scaling,@NativeType("struct aiQuaternion *") AIQuaternion rotation,@NativeType("struct aiVector3D *") AIVector3D position){
  naiDecomposeMatrix(mat.address(),scaling.address(),rotation.address(),position.address());
}
