protected void textEmitChars(final char[] buffer){
  ensureCapacity(buffer.length);
  for (  char aBuffer : buffer) {
    text[textLen++]=aBuffer;
  }
}
