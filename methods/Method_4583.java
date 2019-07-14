private void writeSubtitleSampleData(ExtractorInput input,byte[] samplePrefix,int size) throws IOException, InterruptedException {
  int sizeWithPrefix=samplePrefix.length + size;
  if (subtitleSample.capacity() < sizeWithPrefix) {
    subtitleSample.data=Arrays.copyOf(samplePrefix,sizeWithPrefix + size);
  }
 else {
    System.arraycopy(samplePrefix,0,subtitleSample.data,0,samplePrefix.length);
  }
  input.readFully(subtitleSample.data,samplePrefix.length,size);
  subtitleSample.reset(sizeWithPrefix);
}
