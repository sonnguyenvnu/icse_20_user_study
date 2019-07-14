/** 
 * Unsafe version of:  {@link #aiGetMaterialTexture GetMaterialTexture} 
 */
public static int naiGetMaterialTexture(long pMat,int type,int index,long path,long mapping,long uvindex,long blend,long op,long mapmode,long flags){
  long __functionAddress=Functions.GetMaterialTexture;
  if (CHECKS) {
    AIMaterial.validate(pMat);
  }
  return invokePPPPPPPPI(pMat,type,index,path,mapping,uvindex,blend,op,mapmode,flags,__functionAddress);
}
