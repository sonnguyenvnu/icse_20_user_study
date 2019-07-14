public ID3v2Encoding readEncoding() throws IOException, ID3v2Exception {
  byte value=data.readByte();
switch (value) {
case 0:
    return ID3v2Encoding.ISO_8859_1;
case 1:
  return ID3v2Encoding.UTF_16;
case 2:
return ID3v2Encoding.UTF_16BE;
case 3:
return ID3v2Encoding.UTF_8;
default :
break;
}
throw new ID3v2Exception("Invalid encoding: " + value);
}
