@Override protected int supportsFormatInternal(DrmSessionManager<ExoMediaCrypto> drmSessionManager,Format format){
  Assertions.checkNotNull(format.sampleMimeType);
  if (!FfmpegLibrary.supportsFormat(format.sampleMimeType,format.pcmEncoding) || !isOutputSupported(format)) {
    return FORMAT_UNSUPPORTED_SUBTYPE;
  }
 else   if (!supportsFormatDrm(drmSessionManager,format.drmInitData)) {
    return FORMAT_UNSUPPORTED_DRM;
  }
 else {
    return FORMAT_HANDLED;
  }
}
