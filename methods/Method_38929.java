private void ensureCapacity(final int growth){
  int desiredLen=textLen + growth;
  if (desiredLen > text.length) {
    text=ArraysUtil.resize(text,Math.max(textLen << 1,desiredLen));
  }
}
