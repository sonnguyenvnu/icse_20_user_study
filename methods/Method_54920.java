public static int b3GetStatusInverseDynamicsJointForces(@NativeType("b3SharedMemoryStatusHandle") long statusHandle,@Nullable @NativeType("int *") IntBuffer bodyUniqueId,@Nullable @NativeType("int *") IntBuffer dofCount,@Nullable @NativeType("double *") DoubleBuffer jointForces){
  if (CHECKS) {
    check(statusHandle);
    checkSafe(bodyUniqueId,1);
    checkSafe(dofCount,1);
  }
  return nb3GetStatusInverseDynamicsJointForces(statusHandle,memAddressSafe(bodyUniqueId),memAddressSafe(dofCount),memAddressSafe(jointForces));
}
