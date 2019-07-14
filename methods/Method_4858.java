/** 
 * Whether the decoder supports the given  {@code codec}. If there is insufficient information to decide, returns true.
 * @param codec Codec string as defined in RFC 6381.
 * @return True if the given codec is supported by the decoder.
 */
public boolean isCodecSupported(String codec){
  if (codec == null || mimeType == null) {
    return true;
  }
  String codecMimeType=MimeTypes.getMediaMimeType(codec);
  if (codecMimeType == null) {
    return true;
  }
  if (!mimeType.equals(codecMimeType)) {
    logNoSupport("codec.mime " + codec + ", " + codecMimeType);
    return false;
  }
  Pair<Integer,Integer> codecProfileAndLevel=MediaCodecUtil.getCodecProfileAndLevel(codec);
  if (codecProfileAndLevel == null) {
    return true;
  }
  int profile=codecProfileAndLevel.first;
  int level=codecProfileAndLevel.second;
  if (!isVideo && profile != CodecProfileLevel.AACObjectXHE) {
    return true;
  }
  for (  CodecProfileLevel capabilities : getProfileLevels()) {
    if (capabilities.profile == profile && capabilities.level >= level) {
      return true;
    }
  }
  logNoSupport("codec.profileLevel, " + codec + ", " + codecMimeType);
  return false;
}
