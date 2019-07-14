public static ImageLocation getForUser(TLRPC.User user,boolean big){
  if (user == null || user.access_hash == 0 || user.photo == null) {
    return null;
  }
  TLRPC.FileLocation fileLocation=big ? user.photo.photo_big : user.photo.photo_small;
  if (fileLocation == null) {
    return null;
  }
  TLRPC.TL_inputPeerUser inputPeer=new TLRPC.TL_inputPeerUser();
  inputPeer.user_id=user.id;
  inputPeer.access_hash=user.access_hash;
  int dc_id;
  if (user.photo.dc_id != 0) {
    dc_id=user.photo.dc_id;
  }
 else {
    dc_id=fileLocation.dc_id;
  }
  return getForPhoto(fileLocation,0,null,null,inputPeer,big,dc_id,null,null);
}
