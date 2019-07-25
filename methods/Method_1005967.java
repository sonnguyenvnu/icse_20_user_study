private Frame decode(){
  if (headerCnt < HEADER_SIZE) {
    int headerCount=read(header,headerCnt,HEADER_SIZE - headerCnt);
    if (headerCount == 0) {
      return null;
    }
    headerCnt+=headerCount;
    streamType=streamType(header[0]);
    if (streamType.equals(StreamType.RAW)) {
      return new Frame(streamType,Arrays.copyOf(header,headerCount));
    }
    if (headerCnt < HEADER_SIZE) {
      return null;
    }
  }
  if (streamType.equals(StreamType.RAW)) {
    if (payloadCnt == 0) {
      payload=new byte[rawBuffer.readableBytes()];
    }
    int count=read(payload,payloadCnt,rawBuffer.readableBytes());
    if (count == 0) {
      return null;
    }
    payloadCnt=0;
    return new Frame(StreamType.RAW,payload);
  }
 else {
    int payloadSize=((header[4] & 0xff) << 24) + ((header[5] & 0xff) << 16) + ((header[6] & 0xff) << 8) + (header[7] & 0xff);
    if (payloadCnt == 0) {
      payload=new byte[payloadSize];
    }
    int count=read(payload,payloadCnt,payloadSize - payloadCnt);
    if (count == 0) {
      return null;
    }
    payloadCnt+=count;
    if (payloadCnt < payloadSize) {
      return null;
    }
    headerCnt=0;
    payloadCnt=0;
    return new Frame(streamType,payload);
  }
}
