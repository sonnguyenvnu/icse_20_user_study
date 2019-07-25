@Override public long frequency(BytesRef term) throws IOException {
  term=preFilter(term,spare,byteSpare);
  return internalFrequency(term);
}
