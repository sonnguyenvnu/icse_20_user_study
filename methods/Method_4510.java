public boolean isEndOfData(){
  if (byteBufferData != null) {
    return byteBufferData.remaining() == 0;
  }
 else   if (extractorInput != null) {
    return endOfExtractorInput;
  }
  return true;
}
