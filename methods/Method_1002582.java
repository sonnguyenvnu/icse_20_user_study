private ByteBuffer[] encode(HttpMethod method,HeaderMap headers,Object body,String path) throws IOException {
  ByteBuffer bodyBuffer=HttpUtils.bodyBuffer(body);
  if (body != null) {
    headers.putOrReplace("Content-Length",Integer.toString(bodyBuffer.remaining()));
  }
 else {
    headers.putOrReplace("Content-Length","0");
  }
  DynamicBytes bytes=new DynamicBytes(196);
  bytes.append(method.toString()).append(SP).append(path);
  bytes.append(" HTTP/1.1\r\n");
  headers.encodeHeaders(bytes);
  ByteBuffer headBuffer=ByteBuffer.wrap(bytes.get(),0,bytes.length());
  if (bodyBuffer == null) {
    return new ByteBuffer[]{headBuffer};
  }
 else {
    return new ByteBuffer[]{headBuffer,bodyBuffer};
  }
}
