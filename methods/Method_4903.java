/** 
 * Returns the maximum frame size supported by the default H264 decoder.
 * @return The maximum frame size for an H264 stream that can be decoded on the device.
 */
public static int maxH264DecodableFrameSize() throws DecoderQueryException {
  if (maxH264DecodableFrameSize == -1) {
    int result=0;
    MediaCodecInfo decoderInfo=getDecoderInfo(MimeTypes.VIDEO_H264,false);
    if (decoderInfo != null) {
      for (      CodecProfileLevel profileLevel : decoderInfo.getProfileLevels()) {
        result=Math.max(avcLevelToMaxFrameSize(profileLevel.level),result);
      }
      result=Math.max(result,Util.SDK_INT >= 21 ? (720 * 480) : (480 * 360));
    }
    maxH264DecodableFrameSize=result;
  }
  return maxH264DecodableFrameSize;
}
