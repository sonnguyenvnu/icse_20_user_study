/** 
 * Unsafe version of:  {@link #aiSetImportPropertyInteger SetImportPropertyInteger} 
 */
public static void naiSetImportPropertyInteger(long store,long szName,int value){
  long __functionAddress=Functions.SetImportPropertyInteger;
  invokePPV(store,szName,value,__functionAddress);
}
