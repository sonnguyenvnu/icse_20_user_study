public static void b3ConfigureOpenGLVisualizerSetVisualizationFlags(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int flag,@NativeType("int") boolean enabled){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3ConfigureOpenGLVisualizerSetVisualizationFlags(commandHandle,flag,enabled ? 1 : 0);
}
