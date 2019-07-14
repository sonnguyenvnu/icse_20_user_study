public static int ncuMipmappedArrayCreate(long pHandle,long pMipmappedArrayDesc,int numMipmapLevels){
  long __functionAddress=Functions.MipmappedArrayCreate;
  return callPPI(pHandle,pMipmappedArrayDesc,numMipmapLevels,__functionAddress);
}
