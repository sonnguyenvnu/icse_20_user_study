/** 
 * Unsafe version of:  {@link #aiReleaseExportBlob ReleaseExportBlob} 
 */
public static void naiReleaseExportBlob(long pData){
  long __functionAddress=Functions.ReleaseExportBlob;
  if (CHECKS) {
    AIExportDataBlob.validate(pData);
  }
  invokePV(pData,__functionAddress);
}
