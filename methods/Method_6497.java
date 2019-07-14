private byte[] getFileReference(TLRPC.PhotoSize photoSize,TLRPC.InputFileLocation location,boolean[] needReplacement){
  if (photoSize == null || !(location instanceof TLRPC.TL_inputFileLocation)) {
    return null;
  }
  return getFileReference(photoSize.location,location,needReplacement);
}
