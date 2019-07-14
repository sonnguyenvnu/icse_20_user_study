private byte[] getFileReference(TLRPC.Chat chat,TLRPC.InputFileLocation location,boolean[] needReplacement,TLRPC.InputFileLocation[] replacement){
  if (chat == null || chat.photo == null || !(location instanceof TLRPC.TL_inputFileLocation)) {
    return null;
  }
  byte[] result=getFileReference(chat.photo.photo_small,location,needReplacement);
  if (getPeerReferenceReplacement(null,chat,false,location,replacement,needReplacement)) {
    return new byte[0];
  }
  if (result == null) {
    result=getFileReference(chat.photo.photo_big,location,needReplacement);
    if (getPeerReferenceReplacement(null,chat,true,location,replacement,needReplacement)) {
      return new byte[0];
    }
  }
  return result;
}
