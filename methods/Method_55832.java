public static int ncuImportExternalSemaphore(long extSem_out,long semHandleDesc){
  long __functionAddress=Functions.ImportExternalSemaphore;
  return callPPI(extSem_out,semHandleDesc,__functionAddress);
}
