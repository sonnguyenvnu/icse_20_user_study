public ScanParams match(final String pattern){
  params.add(MATCH.raw);
  params.add(SafeEncoder.encode(pattern));
  return this;
}
