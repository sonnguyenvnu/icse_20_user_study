@Override public void reset(CharSequence input){
  super.reset(input);
  Preconditions.checkNotNull(extractor);
  offsetMap.clear();
  extractor.reset(input);
  while (extractor.incrementToken()) {
    offsetMap.put(extractor.offset(),extractor.offset() + extractor.length());
  }
}
