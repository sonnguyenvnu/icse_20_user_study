public static void b3ComputeProjectionMatrixFOV(float fov,float aspect,float nearVal,float farVal,@NativeType("float *") FloatBuffer projectionMatrix){
  if (CHECKS) {
    check(projectionMatrix,16);
  }
  nb3ComputeProjectionMatrixFOV(fov,aspect,nearVal,farVal,memAddress(projectionMatrix));
}
