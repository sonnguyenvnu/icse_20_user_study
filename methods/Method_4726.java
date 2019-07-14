private void parseSamples(ExtractorInput input) throws IOException, InterruptedException {
  for (; remainingSampleCount > 0; remainingSampleCount--) {
    dataScratch.reset();
    input.readFully(dataScratch.data,0,3);
    trackOutput.sampleData(dataScratch,3);
    sampleBytesWritten+=3;
  }
  if (sampleBytesWritten > 0) {
    trackOutput.sampleMetadata(timestampUs,C.BUFFER_FLAG_KEY_FRAME,sampleBytesWritten,0,null);
  }
}
