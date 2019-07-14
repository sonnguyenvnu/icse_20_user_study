/** 
 * Multiply two 4x4 matrices.
 * @param dst First factor, receives result.
 * @param src Matrix to be multiplied with 'dst'.
 */
public static void aiMultiplyMatrix4(@NativeType("struct aiMatrix4x4 *") AIMatrix4x4 dst,@NativeType("struct aiMatrix4x4 const *") AIMatrix4x4 src){
  naiMultiplyMatrix4(dst.address(),src.address());
}
