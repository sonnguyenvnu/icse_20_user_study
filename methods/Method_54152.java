/** 
 * Unsafe version of:  {@link #aiGetMaterialIntegerArray GetMaterialIntegerArray}
 * @param pMax Specifies the size of the given buffer, in int's. Receives the number of values (not bytes!) read.
 */
public static int naiGetMaterialIntegerArray(long pMat,long pKey,int type,int index,long pOut,long pMax){
  long __functionAddress=Functions.GetMaterialIntegerArray;
  if (CHECKS) {
    AIMaterial.validate(pMat);
  }
  return invokePPPPI(pMat,pKey,type,index,pOut,pMax,__functionAddress);
}
