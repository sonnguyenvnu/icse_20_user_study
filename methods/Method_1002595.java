public HttpRequest decode(ByteBuffer buffer) throws LineTooLargeException, ProtocolException, RequestTooLargeException {
  String line;
  while (buffer.hasRemaining()) {
switch (state) {
case ALL_READ:
      return request;
case CONNECTION_OPEN:
    line=lineReader.readLine(buffer);
  if (line != null) {
    if (parseProxyLine(line)) {
      state=State.READ_INITIAL;
    }
 else     if (proxyProtocolOption == ProxyProtocolOption.OPTIONAL) {
      createRequest(line);
      state=State.READ_HEADER;
    }
 else {
      throw new ProtocolException("Expected PROXY header, got: " + line);
    }
  }
break;
case READ_INITIAL:
line=lineReader.readLine(buffer);
if (line != null) {
createRequest(line);
state=State.READ_HEADER;
}
break;
case READ_HEADER:
readHeaders(buffer);
break;
case READ_CHUNK_SIZE:
line=lineReader.readLine(buffer);
if (line != null) {
readRemaining=getChunkSize(line);
if (readRemaining == 0) {
state=State.READ_CHUNK_FOOTER;
}
 else {
throwIfBodyIsTooLarge();
if (content == null) {
content=new byte[readRemaining];
}
 else if (content.length < readCount + readRemaining) {
int newLength=(int)((readRemaining + readCount) * 1.3);
content=Arrays.copyOf(content,newLength);
}
state=State.READ_CHUNKED_CONTENT;
}
}
break;
case READ_FIXED_LENGTH_CONTENT:
readFixedLength(buffer);
if (readRemaining == 0) {
finish();
}
break;
case READ_CHUNKED_CONTENT:
readFixedLength(buffer);
if (readRemaining == 0) {
state=State.READ_CHUNK_DELIMITER;
}
break;
case READ_CHUNK_FOOTER:
readEmptyLine(buffer);
finish();
break;
case READ_CHUNK_DELIMITER:
readEmptyLine(buffer);
state=State.READ_CHUNK_SIZE;
break;
}
}
return state == State.ALL_READ ? request : null;
}
