/** 
 * Ensures  {@link #scratch} contains at least {@code requiredLength} bytes of data, reading fromthe extractor input if necessary.
 */
private void readScratch(ExtractorInput input,int requiredLength) throws IOException, InterruptedException {
  if (scratch.limit() >= requiredLength) {
    return;
  }
  if (scratch.capacity() < requiredLength) {
    scratch.reset(Arrays.copyOf(scratch.data,Math.max(scratch.data.length * 2,requiredLength)),scratch.limit());
  }
  input.readFully(scratch.data,scratch.limit(),requiredLength - scratch.limit());
  scratch.setLimit(requiredLength);
}
