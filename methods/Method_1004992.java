@Override public Long apply(final FreqMap freqMap){
  if (null != freqMap) {
    return freqMap.get(key);
  }
  return null;
}
