/** 
 * Construct a quaternion from a 3x3 rotation matrix.
 * @param quat Receives the output quaternion.
 * @param mat  Matrix to 'quaternionize'.
 */
public static void aiCreateQuaternionFromMatrix(@NativeType("struct aiQuaternion *") AIQuaternion quat,@NativeType("struct aiMatrix3x3 const *") AIMatrix3x3 mat){
  naiCreateQuaternionFromMatrix(quat.address(),mat.address());
}
