/** 
 * Unsafe version of:  {@link #aiGetMaterialFloatArray GetMaterialFloatArray}
 * @param pMax Specifies the size of the given buffer, in float's. Receives the number of values (not bytes!) read.
 */
public static int naiGetMaterialFloatArray(long pMat,long pKey,int type,int index,long pOut,long pMax){
  long __functionAddress=Functions.GetMaterialFloatArray;
  if (CHECKS) {
    AIMaterial.validate(pMat);
  }
  return invokePPPPI(pMat,pKey,type,index,pOut,pMax,__functionAddress);
}
