private byte[] getFileReference(TLRPC.User user,TLRPC.InputFileLocation location,boolean[] needReplacement,TLRPC.InputFileLocation[] replacement){
  if (user == null || user.photo == null || !(location instanceof TLRPC.TL_inputFileLocation)) {
    return null;
  }
  byte[] result=getFileReference(user.photo.photo_small,location,needReplacement);
  if (getPeerReferenceReplacement(user,null,false,location,replacement,needReplacement)) {
    return new byte[0];
  }
  if (result == null) {
    result=getFileReference(user.photo.photo_big,location,needReplacement);
    if (getPeerReferenceReplacement(user,null,true,location,replacement,needReplacement)) {
      return new byte[0];
    }
  }
  return result;
}
