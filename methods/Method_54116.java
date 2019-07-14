/** 
 * Unsafe version of:  {@link #aiCopyScene CopyScene} 
 */
public static void naiCopyScene(long pIn,long pOut){
  long __functionAddress=Functions.CopyScene;
  if (CHECKS) {
    AIScene.validate(pIn);
  }
  invokePPV(pIn,pOut,__functionAddress);
}
