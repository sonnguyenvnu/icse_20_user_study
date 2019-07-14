/** 
 * Returns whether it is possible to adapt the decoder seamlessly from  {@code oldFormat} to {@code newFormat}. If  {@code newFormat} may not be completely populated, pass {@code false} for {@code isNewFormatComplete}.
 * @param oldFormat The format being decoded.
 * @param newFormat The new format.
 * @param isNewFormatComplete Whether {@code newFormat} is populated with format-specificmetadata.
 * @return Whether it is possible to adapt the decoder seamlessly.
 */
public boolean isSeamlessAdaptationSupported(Format oldFormat,Format newFormat,boolean isNewFormatComplete){
  if (isVideo) {
    return oldFormat.sampleMimeType.equals(newFormat.sampleMimeType) && oldFormat.rotationDegrees == newFormat.rotationDegrees && (adaptive || (oldFormat.width == newFormat.width && oldFormat.height == newFormat.height)) && ((!isNewFormatComplete && newFormat.colorInfo == null) || Util.areEqual(oldFormat.colorInfo,newFormat.colorInfo));
  }
 else {
    if (!MimeTypes.AUDIO_AAC.equals(mimeType) || !oldFormat.sampleMimeType.equals(newFormat.sampleMimeType) || oldFormat.channelCount != newFormat.channelCount || oldFormat.sampleRate != newFormat.sampleRate) {
      return false;
    }
    Pair<Integer,Integer> oldCodecProfileLevel=MediaCodecUtil.getCodecProfileAndLevel(oldFormat.codecs);
    Pair<Integer,Integer> newCodecProfileLevel=MediaCodecUtil.getCodecProfileAndLevel(newFormat.codecs);
    if (oldCodecProfileLevel == null || newCodecProfileLevel == null) {
      return false;
    }
    int oldProfile=oldCodecProfileLevel.first;
    int newProfile=newCodecProfileLevel.first;
    return oldProfile == CodecProfileLevel.AACObjectXHE && newProfile == CodecProfileLevel.AACObjectXHE;
  }
}
