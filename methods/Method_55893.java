public static int ncuLaunchCooperativeKernel(long f,int gridDimX,int gridDimY,int gridDimZ,int blockDimX,int blockDimY,int blockDimZ,int sharedMemBytes,long hStream,long kernelParams){
  long __functionAddress=Functions.LaunchCooperativeKernel;
  if (CHECKS) {
    check(f);
  }
  return callPPPI(f,gridDimX,gridDimY,gridDimZ,blockDimX,blockDimY,blockDimZ,sharedMemBytes,hStream,kernelParams,__functionAddress);
}
