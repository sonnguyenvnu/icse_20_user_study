private void readGifInfo() throws IOException {
  validateAndIgnoreHeader();
  final int[] control=new int[]{0,0};
  boolean done=false;
  while (!done) {
    int code=readNextByte();
switch (code) {
case 0x21:
      int extCode=readNextByte();
switch (extCode) {
case 0xff:
      readBlock();
    if (isNetscape()) {
      readNetscapeExtension();
    }
 else {
      skipExtension();
    }
  break;
case 0xf9:
readGraphicsControlExtension(control);
break;
case 0x01:
addFrame(control);
skipExtension();
break;
default :
skipExtension();
}
break;
case 0x2C:
addFrame(control);
skipImage();
break;
case 0x3b:
done=true;
break;
default :
throw new IOException("Unknown block header [" + Integer.toHexString(code) + "]");
}
}
}
