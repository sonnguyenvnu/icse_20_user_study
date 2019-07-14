static protected PImage loadTIFF(byte tiff[]){
  if ((tiff[42] != tiff[102]) || (tiff[43] != tiff[103])) {
    System.err.println(TIFF_ERROR);
    return null;
  }
  int width=((tiff[30] & 0xff) << 8) | (tiff[31] & 0xff);
  int height=((tiff[42] & 0xff) << 8) | (tiff[43] & 0xff);
  int count=((tiff[114] & 0xff) << 24) | ((tiff[115] & 0xff) << 16) | ((tiff[116] & 0xff) << 8) | (tiff[117] & 0xff);
  if (count != width * height * 3) {
    System.err.println(TIFF_ERROR + " (" + width + ", " + height + ")");
    return null;
  }
  for (int i=0; i < TIFF_HEADER.length; i++) {
    if ((i == 30) || (i == 31) || (i == 42) || (i == 43) || (i == 102) || (i == 103) || (i == 114) || (i == 115) || (i == 116) || (i == 117))     continue;
    if (tiff[i] != TIFF_HEADER[i]) {
      System.err.println(TIFF_ERROR + " (" + i + ")");
      return null;
    }
  }
  PImage outgoing=new PImage(width,height,RGB);
  int index=768;
  count/=3;
  for (int i=0; i < count; i++) {
    outgoing.pixels[i]=0xFF000000 | (tiff[index++] & 0xff) << 16 | (tiff[index++] & 0xff) << 8 | (tiff[index++] & 0xff);
  }
  return outgoing;
}
