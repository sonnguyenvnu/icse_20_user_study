private int readFromExtractorInput(int offset,int length) throws IOException, InterruptedException {
  int read=extractorInput.read(tempBuffer,offset,length);
  if (read == C.RESULT_END_OF_INPUT) {
    endOfExtractorInput=true;
    read=0;
  }
  return read;
}
