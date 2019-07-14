@Nullable private static ApicFrame parseCoverArt(ParsableByteArray data){
  int atomSize=data.readInt();
  int atomType=data.readInt();
  if (atomType == Atom.TYPE_data) {
    int fullVersionInt=data.readInt();
    int flags=Atom.parseFullAtomFlags(fullVersionInt);
    String mimeType=flags == 13 ? "image/jpeg" : flags == 14 ? "image/png" : null;
    if (mimeType == null) {
      Log.w(TAG,"Unrecognized cover art flags: " + flags);
      return null;
    }
    data.skipBytes(4);
    byte[] pictureData=new byte[atomSize - 16];
    data.readBytes(pictureData,0,pictureData.length);
    return new ApicFrame(mimeType,null,PICTURE_TYPE_FRONT_COVER,pictureData);
  }
  Log.w(TAG,"Failed to parse cover art attribute");
  return null;
}
