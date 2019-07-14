public static int ncuGraphicsGLRegisterImage(long pCudaResource,int image,int target,int Flags){
  long __functionAddress=Functions.GraphicsGLRegisterImage;
  return callPI(pCudaResource,image,target,Flags,__functionAddress);
}
