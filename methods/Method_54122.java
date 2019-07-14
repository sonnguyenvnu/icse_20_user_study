/** 
 * Unsafe version of:  {@link #aiImportFileFromMemoryWithProperties ImportFileFromMemoryWithProperties}
 * @param pLength Length of pBuffer, in bytes
 */
public static long naiImportFileFromMemoryWithProperties(long pBuffer,int pLength,int pFlags,long pHint,long pProps){
  long __functionAddress=Functions.ImportFileFromMemoryWithProperties;
  return invokePPPP(pBuffer,pLength,pFlags,pHint,pProps,__functionAddress);
}
