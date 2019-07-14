@Override public boolean sniff(ExtractorInput input) throws IOException, InterruptedException {
  return readAmrHeader(input);
}
