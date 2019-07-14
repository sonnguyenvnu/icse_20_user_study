private void processByte(byte byteToProcess){
  if (mUtf8ToFollow > 0) {
    if ((byteToProcess & 0b11000000) == 0b10000000) {
      mUtf8InputBuffer[mUtf8Index++]=byteToProcess;
      if (--mUtf8ToFollow == 0) {
        byte firstByteMask=(byte)(mUtf8Index == 2 ? 0b00011111 : (mUtf8Index == 3 ? 0b00001111 : 0b00000111));
        int codePoint=(mUtf8InputBuffer[0] & firstByteMask);
        for (int i=1; i < mUtf8Index; i++)         codePoint=((codePoint << 6) | (mUtf8InputBuffer[i] & 0b00111111));
        if (((codePoint <= 0b1111111) && mUtf8Index > 1) || (codePoint < 0b11111111111 && mUtf8Index > 2) || (codePoint < 0b1111111111111111 && mUtf8Index > 3)) {
          codePoint=UNICODE_REPLACEMENT_CHAR;
        }
        mUtf8Index=mUtf8ToFollow=0;
        if (codePoint >= 0x80 && codePoint <= 0x9F) {
        }
 else {
switch (Character.getType(codePoint)) {
case Character.UNASSIGNED:
case Character.SURROGATE:
            codePoint=UNICODE_REPLACEMENT_CHAR;
        }
        processCodePoint(codePoint);
      }
    }
  }
 else {
    mUtf8Index=mUtf8ToFollow=0;
    emitCodePoint(UNICODE_REPLACEMENT_CHAR);
    processByte(byteToProcess);
  }
}
 else {
  if ((byteToProcess & 0b10000000) == 0) {
    processCodePoint(byteToProcess);
    return;
  }
 else   if ((byteToProcess & 0b11100000) == 0b11000000) {
    mUtf8ToFollow=1;
  }
 else   if ((byteToProcess & 0b11110000) == 0b11100000) {
    mUtf8ToFollow=2;
  }
 else   if ((byteToProcess & 0b11111000) == 0b11110000) {
    mUtf8ToFollow=3;
  }
 else {
    processCodePoint(UNICODE_REPLACEMENT_CHAR);
    return;
  }
  mUtf8InputBuffer[mUtf8Index++]=byteToProcess;
}
}
