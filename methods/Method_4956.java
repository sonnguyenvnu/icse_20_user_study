private static @Nullable UrlLinkFrame decodeWxxxFrame(ParsableByteArray id3Data,int frameSize) throws UnsupportedEncodingException {
  if (frameSize < 1) {
    return null;
  }
  int encoding=id3Data.readUnsignedByte();
  String charset=getCharsetName(encoding);
  byte[] data=new byte[frameSize - 1];
  id3Data.readBytes(data,0,frameSize - 1);
  int descriptionEndIndex=indexOfEos(data,0,encoding);
  String description=new String(data,0,descriptionEndIndex,charset);
  int urlStartIndex=descriptionEndIndex + delimiterLength(encoding);
  int urlEndIndex=indexOfZeroByte(data,urlStartIndex);
  String url=decodeStringIfValid(data,urlStartIndex,urlEndIndex,"ISO-8859-1");
  return new UrlLinkFrame("WXXX",description,url);
}
