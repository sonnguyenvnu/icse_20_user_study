private static ChunkExtractorWrapper newWrappedExtractor(int trackType,Format format){
  String mimeType=format.containerMimeType;
  boolean isWebm=mimeType != null && (mimeType.startsWith(MimeTypes.VIDEO_WEBM) || mimeType.startsWith(MimeTypes.AUDIO_WEBM));
  Extractor extractor=isWebm ? new MatroskaExtractor() : new FragmentedMp4Extractor();
  return new ChunkExtractorWrapper(extractor,trackType,format);
}
