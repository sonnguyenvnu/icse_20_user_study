@NativeType("CUresult") public static int cuLaunchHostFunc(@NativeType("CUstream") long hStream,@NativeType("void (*) (void *)") CUhostFnI fn,@NativeType("void *") long userData){
  return ncuLaunchHostFunc(hStream,fn.address(),userData);
}
