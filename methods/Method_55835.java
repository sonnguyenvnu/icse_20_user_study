@NativeType("CUresult") public static int cuSignalExternalSemaphoresAsync(@NativeType("CUexternalSemaphore const *") PointerBuffer extSemArray,@Nullable @NativeType("CUDA_EXTERNAL_SEMAPHORE_SIGNAL_PARAMS const *") CUDA_EXTERNAL_SEMAPHORE_SIGNAL_PARAMS.Buffer paramsArray,@NativeType("CUstream") long stream){
  if (CHECKS) {
    checkSafe(paramsArray,extSemArray.remaining());
  }
  return ncuSignalExternalSemaphoresAsync(memAddress(extSemArray),memAddressSafe(paramsArray),extSemArray.remaining(),stream);
}
