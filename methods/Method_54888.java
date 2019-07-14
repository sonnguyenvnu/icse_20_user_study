public static void b3RequestCameraImageSetProjectiveTextureMatrices(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,@NativeType("float *") FloatBuffer viewMatrix,@NativeType("float *") FloatBuffer projectionMatrix){
  if (CHECKS) {
    check(commandHandle);
    check(viewMatrix,16);
    check(projectionMatrix,16);
  }
  nb3RequestCameraImageSetProjectiveTextureMatrices(commandHandle,memAddress(viewMatrix),memAddress(projectionMatrix));
}
