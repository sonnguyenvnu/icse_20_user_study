/** 
 * Returns a maximum input size for a given codec, MIME type, width and height.
 * @param codecInfo Information about the {@link MediaCodec} being configured.
 * @param sampleMimeType The format mime type.
 * @param width The width in pixels.
 * @param height The height in pixels.
 * @return A maximum input size in bytes, or {@link Format#NO_VALUE} if a maximum could not bedetermined.
 */
private static int getCodecMaxInputSize(MediaCodecInfo codecInfo,String sampleMimeType,int width,int height){
  if (width == Format.NO_VALUE || height == Format.NO_VALUE) {
    return Format.NO_VALUE;
  }
  int maxPixels;
  int minCompressionRatio;
switch (sampleMimeType) {
case MimeTypes.VIDEO_H263:
case MimeTypes.VIDEO_MP4V:
    maxPixels=width * height;
  minCompressionRatio=2;
break;
case MimeTypes.VIDEO_H264:
if ("BRAVIA 4K 2015".equals(Util.MODEL) || ("Amazon".equals(Util.MANUFACTURER) && ("KFSOWI".equals(Util.MODEL) || ("AFTS".equals(Util.MODEL) && codecInfo.secure)))) {
return Format.NO_VALUE;
}
maxPixels=Util.ceilDivide(width,16) * Util.ceilDivide(height,16) * 16 * 16;
minCompressionRatio=2;
break;
case MimeTypes.VIDEO_VP8:
maxPixels=width * height;
minCompressionRatio=2;
break;
case MimeTypes.VIDEO_H265:
case MimeTypes.VIDEO_VP9:
maxPixels=width * height;
minCompressionRatio=4;
break;
default :
return Format.NO_VALUE;
}
return (maxPixels * 3) / (2 * minCompressionRatio);
}
