/** 
 * Unsafe version of:  {@link #aiGetMaterialProperty GetMaterialProperty} 
 */
public static int naiGetMaterialProperty(long pMat,long pKey,int type,int index,long mPropOut){
  long __functionAddress=Functions.GetMaterialProperty;
  if (CHECKS) {
    AIMaterial.validate(pMat);
  }
  return invokePPPI(pMat,pKey,type,index,mPropOut,__functionAddress);
}
