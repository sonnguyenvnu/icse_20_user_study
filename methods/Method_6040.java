@Override protected boolean shouldInitCodec(MediaCodecInfo codecInfo){
  return surface != null || shouldUseDummySurface(codecInfo);
}
