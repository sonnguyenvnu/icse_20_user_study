@Override public String produce(){
  assert headers != null : "headers has not been initialized yet.";
  if (produced != null) {
    return produced;
  }
  try {
    return produced=reproduce(headers,Unpooled.wrappedBuffer(bufferList.toArray(BYTE_BUFS)));
  }
  finally {
    bufferList.forEach(ReferenceCountUtil::safeRelease);
    bufferList.clear();
  }
}
