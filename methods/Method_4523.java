@Override protected int supportsFormatInternal(DrmSessionManager<ExoMediaCrypto> drmSessionManager,Format format){
  if (!MimeTypes.AUDIO_FLAC.equalsIgnoreCase(format.sampleMimeType)) {
    return FORMAT_UNSUPPORTED_TYPE;
  }
 else   if (!supportsOutput(format.channelCount,C.ENCODING_PCM_16BIT)) {
    return FORMAT_UNSUPPORTED_SUBTYPE;
  }
 else   if (!supportsFormatDrm(drmSessionManager,format.drmInitData)) {
    return FORMAT_UNSUPPORTED_DRM;
  }
 else {
    return FORMAT_HANDLED;
  }
}
