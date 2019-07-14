/** 
 * Unsafe version of:  {@link #aiGetMaterialTextureCount GetMaterialTextureCount} 
 */
public static int naiGetMaterialTextureCount(long pMat,int type){
  long __functionAddress=Functions.GetMaterialTextureCount;
  if (CHECKS) {
    AIMaterial.validate(pMat);
  }
  return invokePI(pMat,type,__functionAddress);
}
