private boolean parseHeader(ExtractorInput input) throws IOException, InterruptedException {
  dataScratch.reset();
  if (input.readFully(dataScratch.data,0,HEADER_SIZE,true)) {
    if (dataScratch.readInt() != HEADER_ID) {
      throw new IOException("Input not RawCC");
    }
    version=dataScratch.readUnsignedByte();
    return true;
  }
 else {
    return false;
  }
}
