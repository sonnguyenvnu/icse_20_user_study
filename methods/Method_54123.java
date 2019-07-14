/** 
 * Unsafe version of:  {@link #aiApplyPostProcessing ApplyPostProcessing} 
 */
public static long naiApplyPostProcessing(long pScene,int pFlags){
  long __functionAddress=Functions.ApplyPostProcessing;
  if (CHECKS) {
    AIScene.validate(pScene);
  }
  return invokePP(pScene,pFlags,__functionAddress);
}
