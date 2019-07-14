public static void b3RequestCameraImageSetLightSpecularCoeff(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,float lightSpecularCoeff){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3RequestCameraImageSetLightSpecularCoeff(commandHandle,lightSpecularCoeff);
}
