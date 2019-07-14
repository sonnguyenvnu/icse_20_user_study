private int computeSize(){
  srcWidth=srcWidth % 2 == 1 ? srcWidth + 1 : srcWidth;
  srcHeight=srcHeight % 2 == 1 ? srcHeight + 1 : srcHeight;
  int longSide=Math.max(srcWidth,srcHeight);
  int shortSide=Math.min(srcWidth,srcHeight);
  float scale=((float)shortSide / longSide);
  if (scale <= 1 && scale > 0.5625) {
    if (longSide < 1664) {
      return 2;
    }
 else     if (longSide >= 1664 && longSide < 4990) {
      return 4;
    }
 else     if (longSide > 4990 && longSide < 10240) {
      return 8;
    }
 else {
      return longSide / 1280 == 0 ? 1 + 1 : longSide / 1280 + 1;
    }
  }
 else   if (scale <= 0.5625 && scale > 0.5) {
    return longSide / 1280 == 0 ? 1 + 1 : longSide / 1280 + 1;
  }
 else {
    return (int)Math.ceil(longSide / (1280.0 / scale)) + 1;
  }
}
