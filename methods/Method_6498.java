private byte[] getFileReference(TLRPC.FileLocation fileLocation,TLRPC.InputFileLocation location,boolean[] needReplacement){
  if (fileLocation == null || !(location instanceof TLRPC.TL_inputFileLocation)) {
    return null;
  }
  if (fileLocation.local_id == location.local_id && fileLocation.volume_id == location.volume_id) {
    if (fileLocation.file_reference == null && needReplacement != null) {
      needReplacement[0]=true;
    }
    return fileLocation.file_reference;
  }
  return null;
}
