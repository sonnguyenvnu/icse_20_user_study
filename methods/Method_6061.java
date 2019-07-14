private boolean shouldUseDummySurface(MediaCodecInfo codecInfo){
  return Util.SDK_INT >= 23 && !tunneling && !codecNeedsSetOutputSurfaceWorkaround(codecInfo.name) && (!codecInfo.secure || DummySurface.isSecureSupported(context));
}
