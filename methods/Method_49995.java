protected static byte[] getWapString(ByteArrayInputStream pduDataStream,int stringType){
  assert (null != pduDataStream);
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  int temp=pduDataStream.read();
  assert (-1 != temp);
  while ((-1 != temp) && ('\0' != temp)) {
    if (stringType == TYPE_TOKEN_STRING) {
      if (isTokenCharacter(temp)) {
        out.write(temp);
      }
    }
 else {
      if (isText(temp)) {
        out.write(temp);
      }
    }
    temp=pduDataStream.read();
    assert (-1 != temp);
  }
  if (out.size() > 0) {
    return out.toByteArray();
  }
  return null;
}
