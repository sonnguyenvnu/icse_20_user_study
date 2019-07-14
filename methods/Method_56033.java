@NativeType("CUresult") public static int cuGraphicsGLRegisterImage(@NativeType("CUgraphicsResource *") PointerBuffer pCudaResource,@NativeType("GLuint") int image,@NativeType("GLenum") int target,@NativeType("unsigned int") int Flags){
  if (CHECKS) {
    check(pCudaResource,1);
  }
  return ncuGraphicsGLRegisterImage(memAddress(pCudaResource),image,target,Flags);
}
