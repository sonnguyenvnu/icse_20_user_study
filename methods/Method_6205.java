public String readFixedLengthString(int length,ID3v2Encoding encoding) throws IOException, ID3v2Exception {
  if (length > getRemainingLength()) {
    throw new ID3v2Exception("Could not read fixed-length string of length: " + length);
  }
  byte[] bytes=textBuffer.get().bytes(length);
  data.readFully(bytes,0,length);
  return extractString(bytes,0,length,encoding,true);
}
