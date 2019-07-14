private static Pair<Extractor,Boolean> buildResult(Extractor extractor){
  return new Pair<>(extractor,extractor instanceof AdtsExtractor || extractor instanceof Ac3Extractor || extractor instanceof Mp3Extractor);
}
