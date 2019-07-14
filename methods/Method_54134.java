/** 
 * Unsafe version of:  {@link #aiSetImportPropertyFloat SetImportPropertyFloat} 
 */
public static void naiSetImportPropertyFloat(long store,long szName,float value){
  long __functionAddress=Functions.SetImportPropertyFloat;
  invokePPV(store,szName,value,__functionAddress);
}
