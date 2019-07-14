/** 
 * Unsafe version of:  {@link #aiGetMaterialUVTransform GetMaterialUVTransform} 
 */
public static int naiGetMaterialUVTransform(long pMat,long pKey,int type,int index,long pOut){
  long __functionAddress=Functions.GetMaterialUVTransform;
  if (CHECKS) {
    AIMaterial.validate(pMat);
  }
  return invokePPPI(pMat,pKey,type,index,pOut,__functionAddress);
}
