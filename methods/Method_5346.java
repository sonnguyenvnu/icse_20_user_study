private EncryptionKeyChunk newEncryptionKeyChunk(Uri keyUri,String iv,int variantIndex,int trackSelectionReason,Object trackSelectionData){
  DataSpec dataSpec=new DataSpec(keyUri,0,C.LENGTH_UNSET,null,DataSpec.FLAG_ALLOW_GZIP);
  return new EncryptionKeyChunk(encryptionDataSource,dataSpec,variants[variantIndex].format,trackSelectionReason,trackSelectionData,scratchSpace,iv);
}
