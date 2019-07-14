public static void b3ConfigureOpenGLVisualizerSetViewMatrix(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,float cameraDistance,float cameraPitch,float cameraYaw,@NativeType("float const *") FloatBuffer cameraTargetPosition){
  if (CHECKS) {
    check(commandHandle);
    check(cameraTargetPosition,3);
  }
  nb3ConfigureOpenGLVisualizerSetViewMatrix(commandHandle,cameraDistance,cameraPitch,cameraYaw,memAddress(cameraTargetPosition));
}
