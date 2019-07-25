public State decode(ByteBuffer buffer) throws LineTooLargeException, ProtocolException, AbortException {
  String line;
  while (buffer.hasRemaining() && state != State.ALL_READ) {
switch (state) {
case READ_INITIAL:
      if ((line=lineReader.readLine(buffer)) != null) {
        parseInitialLine(line);
      }
    break;
case READ_HEADER:
  readHeaders(buffer);
break;
case READ_CHUNK_SIZE:
line=lineReader.readLine(buffer);
if (line != null && !line.isEmpty()) {
readRemaining=getChunkSize(line);
if (readRemaining == 0) {
state=READ_CHUNK_FOOTER;
}
 else {
state=READ_CHUNKED_CONTENT;
}
}
break;
case READ_FIXED_LENGTH_CONTENT:
readBody(buffer,ALL_READ);
break;
case READ_CHUNKED_CONTENT:
readBody(buffer,READ_CHUNK_DELIMITER);
break;
case READ_CHUNK_FOOTER:
readEmptyLine(buffer,ALL_READ);
break;
case READ_CHUNK_DELIMITER:
readEmptyLine(buffer,READ_CHUNK_SIZE);
break;
case READ_VARIABLE_LENGTH_CONTENT:
readBody(buffer,null);
break;
}
}
return state;
}
