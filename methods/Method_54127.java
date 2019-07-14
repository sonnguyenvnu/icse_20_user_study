/** 
 * Unsafe version of:  {@link #aiReleaseImport ReleaseImport} 
 */
public static void naiReleaseImport(long pScene){
  long __functionAddress=Functions.ReleaseImport;
  if (CHECKS) {
    if (pScene != NULL) {
      AIScene.validate(pScene);
    }
  }
  invokePV(pScene,__functionAddress);
}
