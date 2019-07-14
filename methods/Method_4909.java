private static @Nullable Pair<Integer,Integer> getAacCodecProfileAndLevel(String codec,String[] parts){
  if (parts.length != 3) {
    Log.w(TAG,"Ignoring malformed MP4A codec string: " + codec);
    return null;
  }
  try {
    int objectTypeIndication=Integer.parseInt(parts[1],16);
    String mimeType=MimeTypes.getMimeTypeFromMp4ObjectType(objectTypeIndication);
    if (MimeTypes.AUDIO_AAC.equals(mimeType)) {
      int audioObjectTypeIndication=Integer.parseInt(parts[2]);
      int profile=MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.get(audioObjectTypeIndication,-1);
      if (profile != -1) {
        return new Pair<>(profile,0);
      }
    }
  }
 catch (  NumberFormatException e) {
    Log.w(TAG,"Ignoring malformed MP4A codec string: " + codec);
  }
  return null;
}
