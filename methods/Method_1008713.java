public long frequency(BytesRef term) throws IOException {
  if (termsEnum.seekExact(term)) {
    return useTotalTermFreq ? termsEnum.totalTermFreq() : termsEnum.docFreq();
  }
  return 0;
}
