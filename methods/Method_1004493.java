public int flush(){
  final int value=wrotePosition.get();
  try {
    if (fileChannel.position() != 0) {
      fileChannel.force(false);
    }
 else {
      mappedByteBuffer.force();
    }
  }
 catch (  IOException e) {
    throw new RuntimeException("flush segment failed. segment: " + fileName,e);
  }
  return value;
}
