public static int b3GetStatusActualState2(@NativeType("b3SharedMemoryStatusHandle") long statusHandle,@Nullable @NativeType("int *") IntBuffer bodyUniqueId,@Nullable @NativeType("int *") IntBuffer numLinks,@Nullable @NativeType("int *") IntBuffer numDegreeOfFreedomQ,@Nullable @NativeType("int *") IntBuffer numDegreeOfFreedomU,@Nullable @NativeType("double const **") PointerBuffer rootLocalInertialFrame,@Nullable @NativeType("double const **") PointerBuffer actualStateQ,@Nullable @NativeType("double const **") PointerBuffer actualStateQdot,@Nullable @NativeType("double const **") PointerBuffer jointReactionForces,@Nullable @NativeType("double const **") PointerBuffer linkLocalInertialFrames,@Nullable @NativeType("double const **") PointerBuffer jointMotorForces,@Nullable @NativeType("double const **") PointerBuffer linkStates,@Nullable @NativeType("double const **") PointerBuffer linkWorldVelocities){
  if (CHECKS) {
    check(statusHandle);
    checkSafe(bodyUniqueId,1);
    checkSafe(numLinks,1);
    checkSafe(numDegreeOfFreedomQ,1);
    checkSafe(numDegreeOfFreedomU,1);
    checkSafe(rootLocalInertialFrame,1);
    checkSafe(actualStateQ,1);
    checkSafe(actualStateQdot,1);
    checkSafe(jointReactionForces,1);
    checkSafe(linkLocalInertialFrames,1);
    checkSafe(jointMotorForces,1);
    checkSafe(linkStates,1);
    checkSafe(linkWorldVelocities,1);
  }
  return nb3GetStatusActualState2(statusHandle,memAddressSafe(bodyUniqueId),memAddressSafe(numLinks),memAddressSafe(numDegreeOfFreedomQ),memAddressSafe(numDegreeOfFreedomU),memAddressSafe(rootLocalInertialFrame),memAddressSafe(actualStateQ),memAddressSafe(actualStateQdot),memAddressSafe(jointReactionForces),memAddressSafe(linkLocalInertialFrames),memAddressSafe(jointMotorForces),memAddressSafe(linkStates),memAddressSafe(linkWorldVelocities));
}
