/** 
 * Unsafe version of:  {@link #aiGetMemoryRequirements GetMemoryRequirements} 
 */
public static void naiGetMemoryRequirements(long pIn,long in){
  long __functionAddress=Functions.GetMemoryRequirements;
  if (CHECKS) {
    AIScene.validate(pIn);
  }
  invokePPV(pIn,in,__functionAddress);
}
