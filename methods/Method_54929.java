public static int b3GetStatusInverseKinematicsJointPositions(@NativeType("b3SharedMemoryStatusHandle") long statusHandle,@Nullable @NativeType("int *") IntBuffer bodyUniqueId,@Nullable @NativeType("int *") IntBuffer dofCount,@Nullable @NativeType("double *") DoubleBuffer jointPositions){
  if (CHECKS) {
    check(statusHandle);
    checkSafe(bodyUniqueId,1);
    checkSafe(dofCount,1);
  }
  return nb3GetStatusInverseKinematicsJointPositions(statusHandle,memAddressSafe(bodyUniqueId),memAddressSafe(dofCount),memAddressSafe(jointPositions));
}
