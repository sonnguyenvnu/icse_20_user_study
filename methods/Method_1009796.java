public static int calculator(int width,int height,int colorFormat){
switch (colorFormat) {
case MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420SemiPlanar:
case MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420Planar:
case ImageFormat.NV21:
case ImageFormat.YV12:
    return width * height * 3 / 2;
default :
  return -1;
}
}
