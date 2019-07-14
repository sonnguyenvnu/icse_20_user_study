/** 
 * Read contents from an entity, with a specified maximum. This is a replacement of EntityUtils.toByteArray because that function does not impose a maximum size.
 * @param entity The entity from which to read
 * @param maxBytes The maximum number of bytes to read
 * @return A byte array containing maxBytes or fewer bytes read from the entity
 * @throws IOException Thrown when reading fails for any reason
 */
protected byte[] toByteArray(HttpEntity entity,int maxBytes) throws IOException {
  if (entity == null) {
    return new byte[0];
  }
  try (InputStream is=entity.getContent()){
    int size=(int)entity.getContentLength();
    int readBufferLength=size;
    if (readBufferLength <= 0) {
      readBufferLength=4096;
    }
    readBufferLength=Math.min(readBufferLength,maxBytes);
    ByteArrayBuffer buffer=new ByteArrayBuffer(readBufferLength);
    byte[] tmpBuff=new byte[4096];
    int dataLength;
    while ((dataLength=is.read(tmpBuff)) != -1) {
      if (maxBytes > 0 && (buffer.length() + dataLength) > maxBytes) {
        truncated=true;
        dataLength=maxBytes - buffer.length();
      }
      buffer.append(tmpBuff,0,dataLength);
      if (truncated) {
        break;
      }
    }
    return buffer.toByteArray();
  }
 }
