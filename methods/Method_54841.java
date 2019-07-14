public static int b3GetStatusActualState(@NativeType("b3SharedMemoryStatusHandle") long statusHandle,@Nullable @NativeType("int *") IntBuffer bodyUniqueId,@Nullable @NativeType("int *") IntBuffer numDegreeOfFreedomQ,@Nullable @NativeType("int *") IntBuffer numDegreeOfFreedomU,@Nullable @NativeType("double const **") PointerBuffer rootLocalInertialFrame,@Nullable @NativeType("double const **") PointerBuffer actualStateQ,@Nullable @NativeType("double const **") PointerBuffer actualStateQdot,@Nullable @NativeType("double const **") PointerBuffer jointReactionForces){
  if (CHECKS) {
    check(statusHandle);
    checkSafe(bodyUniqueId,1);
    checkSafe(numDegreeOfFreedomQ,1);
    checkSafe(numDegreeOfFreedomU,1);
    checkSafe(rootLocalInertialFrame,1);
    checkSafe(actualStateQ,1);
    checkSafe(actualStateQdot,1);
    checkSafe(jointReactionForces,1);
  }
  return nb3GetStatusActualState(statusHandle,memAddressSafe(bodyUniqueId),memAddressSafe(numDegreeOfFreedomQ),memAddressSafe(numDegreeOfFreedomU),memAddressSafe(rootLocalInertialFrame),memAddressSafe(actualStateQ),memAddressSafe(actualStateQdot),memAddressSafe(jointReactionForces));
}
