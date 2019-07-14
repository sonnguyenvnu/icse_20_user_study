@Override public boolean sniff(ExtractorInput input) throws IOException, InterruptedException {
  input.peekFully(sampleData,0,HEADER_MIN_LENGTH,false);
  sampleDataWrapper.reset(sampleData,HEADER_MIN_LENGTH);
  if (WebvttParserUtil.isWebvttHeaderLine(sampleDataWrapper)) {
    return true;
  }
  input.peekFully(sampleData,HEADER_MIN_LENGTH,HEADER_MAX_LENGTH - HEADER_MIN_LENGTH,false);
  sampleDataWrapper.reset(sampleData,HEADER_MAX_LENGTH);
  return WebvttParserUtil.isWebvttHeaderLine(sampleDataWrapper);
}
