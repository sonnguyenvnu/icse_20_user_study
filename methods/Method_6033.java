@Override protected int supportsFormat(MediaCodecSelector mediaCodecSelector,DrmSessionManager<FrameworkMediaCrypto> drmSessionManager,Format format) throws DecoderQueryException {
  String mimeType=format.sampleMimeType;
  if (!MimeTypes.isVideo(mimeType)) {
    return FORMAT_UNSUPPORTED_TYPE;
  }
  boolean requiresSecureDecryption=false;
  DrmInitData drmInitData=format.drmInitData;
  if (drmInitData != null) {
    for (int i=0; i < drmInitData.schemeDataCount; i++) {
      requiresSecureDecryption|=drmInitData.get(i).requiresSecureDecryption;
    }
  }
  List<MediaCodecInfo> decoderInfos=mediaCodecSelector.getDecoderInfos(format.sampleMimeType,requiresSecureDecryption);
  if (decoderInfos.isEmpty()) {
    return requiresSecureDecryption && !mediaCodecSelector.getDecoderInfos(format.sampleMimeType,false).isEmpty() ? FORMAT_UNSUPPORTED_DRM : FORMAT_UNSUPPORTED_SUBTYPE;
  }
  if (!supportsFormatDrm(drmSessionManager,drmInitData)) {
    return FORMAT_UNSUPPORTED_DRM;
  }
  MediaCodecInfo decoderInfo=decoderInfos.get(0);
  boolean isFormatSupported=decoderInfo.isFormatSupported(format);
  int adaptiveSupport=decoderInfo.isSeamlessAdaptationSupported(format) ? ADAPTIVE_SEAMLESS : ADAPTIVE_NOT_SEAMLESS;
  int tunnelingSupport=decoderInfo.tunneling ? TUNNELING_SUPPORTED : TUNNELING_NOT_SUPPORTED;
  int formatSupport=isFormatSupported ? FORMAT_HANDLED : FORMAT_EXCEEDS_CAPABILITIES;
  return adaptiveSupport | tunnelingSupport | formatSupport;
}
