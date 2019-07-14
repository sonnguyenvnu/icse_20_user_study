/** 
 * Unsafe version of:  {@link #aiSetImportPropertyString SetImportPropertyString} 
 */
public static void naiSetImportPropertyString(long store,long szName,long value){
  long __functionAddress=Functions.SetImportPropertyString;
  invokePPPV(store,szName,value,__functionAddress);
}
