/** 
 * Given a body unique id and link index, return the dynamics information. 
 */
public static int b3GetDynamicsInfo(@NativeType("b3SharedMemoryStatusHandle") long statusHandle,@NativeType("struct b3DynamicsInfo *") B3DynamicsInfo info){
  if (CHECKS) {
    check(statusHandle);
  }
  return nb3GetDynamicsInfo(statusHandle,info.address());
}
