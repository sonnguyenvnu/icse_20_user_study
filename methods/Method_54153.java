/** 
 * Unsafe version of:  {@link #aiGetMaterialColor GetMaterialColor} 
 */
public static int naiGetMaterialColor(long pMat,long pKey,int type,int index,long pOut){
  long __functionAddress=Functions.GetMaterialColor;
  if (CHECKS) {
    AIMaterial.validate(pMat);
  }
  return invokePPPI(pMat,pKey,type,index,pOut,__functionAddress);
}
