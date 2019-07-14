/** 
 * Changes method access to private and final.
 */
public static int makePrivateFinalAccess(final int access){
  return (access & 0xFFFFFFF0) | AsmUtil.ACC_PRIVATE | AsmUtil.ACC_FINAL;
}
