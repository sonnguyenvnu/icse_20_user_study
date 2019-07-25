protected TermsEnum filter(Terms terms,TermsEnum iterator,LeafReader reader) throws IOException {
  if (iterator == null) {
    return null;
  }
  int docCount=terms.getDocCount();
  if (docCount == -1) {
    docCount=reader.maxDoc();
  }
  if (docCount >= minSegmentSize) {
    final int minFreq=minFrequency > 1.0 ? (int)minFrequency : (int)(docCount * minFrequency);
    final int maxFreq=maxFrequency > 1.0 ? (int)maxFrequency : (int)(docCount * maxFrequency);
    if (minFreq > 1 || maxFreq < docCount) {
      iterator=new FrequencyFilter(iterator,minFreq,maxFreq);
    }
  }
  return iterator;
}
