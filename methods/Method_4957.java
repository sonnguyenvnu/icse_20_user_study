private static PrivFrame decodePrivFrame(ParsableByteArray id3Data,int frameSize) throws UnsupportedEncodingException {
  byte[] data=new byte[frameSize];
  id3Data.readBytes(data,0,frameSize);
  int ownerEndIndex=indexOfZeroByte(data,0);
  String owner=new String(data,0,ownerEndIndex,"ISO-8859-1");
  int privateDataStartIndex=ownerEndIndex + 1;
  byte[] privateData=copyOfRangeIfValid(data,privateDataStartIndex,data.length);
  return new PrivFrame(owner,privateData);
}
