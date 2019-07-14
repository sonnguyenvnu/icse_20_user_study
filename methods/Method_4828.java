@Override public boolean sniff(ExtractorInput input) throws IOException, InterruptedException {
  return WavHeaderReader.peek(input) != null;
}
