/** 
 * Array version of:  {@link #aiGetMaterialTexture GetMaterialTexture} 
 */
@NativeType("aiReturn") public static int aiGetMaterialTexture(@NativeType("struct aiMaterial const *") AIMaterial pMat,@NativeType("aiTextureType") int type,@NativeType("unsigned int") int index,@NativeType("struct aiString *") AIString path,@Nullable @NativeType("aiTextureMapping *") int[] mapping,@Nullable @NativeType("unsigned int *") int[] uvindex,@Nullable @NativeType("float *") float[] blend,@Nullable @NativeType("aiTextureOp *") int[] op,@Nullable @NativeType("aiTextureMapMode *") int[] mapmode,@Nullable @NativeType("unsigned int *") int[] flags){
  long __functionAddress=Functions.GetMaterialTexture;
  if (CHECKS) {
    checkSafe(mapping,1);
    checkSafe(uvindex,1);
    checkSafe(blend,1);
    checkSafe(op,1);
    checkSafe(mapmode,1);
    checkSafe(flags,1);
    AIMaterial.validate(pMat.address());
  }
  return invokePPPPPPPPI(pMat.address(),type,index,path.address(),mapping,uvindex,blend,op,mapmode,flags,__functionAddress);
}
