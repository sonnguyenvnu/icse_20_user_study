/** 
 * Returns a maximum video size to use when configuring a codec for  {@code format} in a waythat will allow possible adaptation to other compatible formats that are expected to have the same aspect ratio, but whose sizes are unknown.
 * @param codecInfo Information about the {@link MediaCodec} being configured.
 * @param format The format for which the codec is being configured.
 * @return The maximum video size to use, or null if the size of {@code format} should be used.
 * @throws DecoderQueryException If an error occurs querying {@code codecInfo}.
 */
private static Point getCodecMaxSize(MediaCodecInfo codecInfo,Format format) throws DecoderQueryException {
  boolean isVerticalVideo=format.height > format.width;
  int formatLongEdgePx=isVerticalVideo ? format.height : format.width;
  int formatShortEdgePx=isVerticalVideo ? format.width : format.height;
  float aspectRatio=(float)formatShortEdgePx / formatLongEdgePx;
  for (  int longEdgePx : STANDARD_LONG_EDGE_VIDEO_PX) {
    int shortEdgePx=(int)(longEdgePx * aspectRatio);
    if (longEdgePx <= formatLongEdgePx || shortEdgePx <= formatShortEdgePx) {
      return null;
    }
 else     if (Util.SDK_INT >= 21) {
      Point alignedSize=codecInfo.alignVideoSizeV21(isVerticalVideo ? shortEdgePx : longEdgePx,isVerticalVideo ? longEdgePx : shortEdgePx);
      float frameRate=format.frameRate;
      if (codecInfo.isVideoSizeAndRateSupportedV21(alignedSize.x,alignedSize.y,frameRate)) {
        return alignedSize;
      }
    }
 else {
      longEdgePx=Util.ceilDivide(longEdgePx,16) * 16;
      shortEdgePx=Util.ceilDivide(shortEdgePx,16) * 16;
      if (longEdgePx * shortEdgePx <= MediaCodecUtil.maxH264DecodableFrameSize()) {
        return new Point(isVerticalVideo ? shortEdgePx : longEdgePx,isVerticalVideo ? longEdgePx : shortEdgePx);
      }
    }
  }
  return null;
}
