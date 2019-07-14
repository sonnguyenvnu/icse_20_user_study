/** 
 * Unsafe version of:  {@link #aiImportFileExWithProperties ImportFileExWithProperties} 
 */
public static long naiImportFileExWithProperties(long pFile,int pFlags,long pFS,long pProps){
  long __functionAddress=Functions.ImportFileExWithProperties;
  if (CHECKS) {
    if (pFS != NULL) {
      AIFileIO.validate(pFS);
    }
  }
  return invokePPPP(pFile,pFlags,pFS,pProps,__functionAddress);
}
