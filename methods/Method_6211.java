String parseTextFrame(ID3v2FrameBody frame) throws IOException, ID3v2Exception {
  ID3v2Encoding encoding=frame.readEncoding();
  return frame.readFixedLengthString((int)frame.getRemainingLength(),encoding);
}
