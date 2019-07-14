@Override public synchronized Extractor[] createExtractors(){
  Extractor[] extractors=new Extractor[FLAC_EXTRACTOR_CONSTRUCTOR == null ? 12 : 13];
  extractors[0]=new MatroskaExtractor(matroskaFlags);
  extractors[1]=new FragmentedMp4Extractor(fragmentedMp4Flags);
  extractors[2]=new Mp4Extractor(mp4Flags);
  extractors[3]=new OggExtractor();
  extractors[4]=new Mp3Extractor(mp3Flags | (constantBitrateSeekingEnabled ? Mp3Extractor.FLAG_ENABLE_CONSTANT_BITRATE_SEEKING : 0));
  extractors[5]=new AdtsExtractor(0,adtsFlags | (constantBitrateSeekingEnabled ? AdtsExtractor.FLAG_ENABLE_CONSTANT_BITRATE_SEEKING : 0));
  extractors[6]=new Ac3Extractor();
  extractors[7]=new TsExtractor(tsMode,tsFlags);
  extractors[8]=new FlvExtractor();
  extractors[9]=new PsExtractor();
  extractors[10]=new WavExtractor();
  extractors[11]=new AmrExtractor(amrFlags | (constantBitrateSeekingEnabled ? AmrExtractor.FLAG_ENABLE_CONSTANT_BITRATE_SEEKING : 0));
  if (FLAC_EXTRACTOR_CONSTRUCTOR != null) {
    try {
      extractors[12]=FLAC_EXTRACTOR_CONSTRUCTOR.newInstance();
    }
 catch (    Exception e) {
      throw new IllegalStateException("Unexpected error creating FLAC extractor",e);
    }
  }
  return extractors;
}
