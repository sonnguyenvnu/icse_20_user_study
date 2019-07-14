@Override public boolean sniff(ExtractorInput input) throws IOException, InterruptedException {
  dataScratch.reset();
  input.peekFully(dataScratch.data,0,HEADER_SIZE);
  return dataScratch.readInt() == HEADER_ID;
}
