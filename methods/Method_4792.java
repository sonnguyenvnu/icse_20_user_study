@Override public void consume(ParsableByteArray data){
  while (data.bytesLeft() > 0) {
switch (state) {
case STATE_FINDING_HEADER:
      findHeader(data);
    break;
case STATE_READING_HEADER:
  readHeaderRemainder(data);
break;
case STATE_READING_FRAME:
readFrameRemainder(data);
break;
default :
throw new IllegalStateException();
}
}
}
