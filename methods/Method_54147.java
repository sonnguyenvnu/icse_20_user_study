/** 
 * Unsafe version of:  {@link #aiGetImporterDesc GetImporterDesc} 
 */
public static long naiGetImporterDesc(long extension){
  long __functionAddress=Functions.GetImporterDesc;
  return invokePP(extension,__functionAddress);
}
