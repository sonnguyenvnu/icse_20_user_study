private void assertValidOffset(){
  Assertions.checkState(byteOffset >= 0 && (byteOffset < byteLimit || (byteOffset == byteLimit && bitOffset == 0)));
}
