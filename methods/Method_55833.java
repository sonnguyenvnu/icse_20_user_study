@NativeType("CUresult") public static int cuImportExternalSemaphore(@NativeType("CUexternalSemaphore *") PointerBuffer extSem_out,@NativeType("CUDA_EXTERNAL_SEMAPHORE_HANDLE_DESC const *") CUDA_EXTERNAL_SEMAPHORE_HANDLE_DESC semHandleDesc){
  if (CHECKS) {
    check(extSem_out,1);
  }
  return ncuImportExternalSemaphore(memAddress(extSem_out),semHandleDesc.address());
}
