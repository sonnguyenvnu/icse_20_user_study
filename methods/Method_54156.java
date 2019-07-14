/** 
 * Unsafe version of:  {@link #aiGetMaterialString GetMaterialString} 
 */
public static int naiGetMaterialString(long pMat,long pKey,int type,int index,long pOut){
  long __functionAddress=Functions.GetMaterialString;
  if (CHECKS) {
    AIMaterial.validate(pMat);
  }
  return invokePPPI(pMat,pKey,type,index,pOut,__functionAddress);
}
