@NativeType("CUresult") public static int cuGraphicsGLRegisterBuffer(@NativeType("CUgraphicsResource *") PointerBuffer pCudaResource,@NativeType("GLuint") int buffer,@NativeType("unsigned int") int Flags){
  if (CHECKS) {
    check(pCudaResource,1);
  }
  return ncuGraphicsGLRegisterBuffer(memAddress(pCudaResource),buffer,Flags);
}
