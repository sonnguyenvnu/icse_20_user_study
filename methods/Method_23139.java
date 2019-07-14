/** 
 * Targa image loader for RLE-compressed TGA files. Code taken from PApplet, any changes here should lead to updates there.
 */
static private BufferedImage loadImageTGA(File file) throws IOException {
  InputStream is=new FileInputStream(file);
  try {
    byte header[]=new byte[18];
    int offset=0;
    do {
      int count=is.read(header,offset,header.length - offset);
      if (count == -1)       return null;
      offset+=count;
    }
 while (offset < 18);
    int format=0;
    final int RGB=1;
    final int ARGB=2;
    final int ALPHA=4;
    if (((header[2] == 3) || (header[2] == 11)) && (header[16] == 8) && ((header[17] == 0x8) || (header[17] == 0x28))) {
      format=ALPHA;
    }
 else     if (((header[2] == 2) || (header[2] == 10)) && (header[16] == 24) && ((header[17] == 0x20) || (header[17] == 0))) {
      format=RGB;
    }
 else     if (((header[2] == 2) || (header[2] == 10)) && (header[16] == 32) && ((header[17] == 0x8) || (header[17] == 0x28))) {
      format=ARGB;
    }
    if (format == 0) {
      throw new IOException(Language.interpolate("movie_maker.error.unknown_tga_format",file.getName()));
    }
    int w=((header[13] & 0xff) << 8) + (header[12] & 0xff);
    int h=((header[15] & 0xff) << 8) + (header[14] & 0xff);
    int[] pixels=new int[w * h];
    boolean reversed=(header[17] & 0x20) == 0;
    if ((header[2] == 2) || (header[2] == 3)) {
      if (reversed) {
        int index=(h - 1) * w;
switch (format) {
case ALPHA:
          for (int y=h - 1; y >= 0; y--) {
            for (int x=0; x < w; x++) {
              pixels[index + x]=is.read();
            }
            index-=w;
          }
        break;
case RGB:
      for (int y=h - 1; y >= 0; y--) {
        for (int x=0; x < w; x++) {
          pixels[index + x]=is.read() | (is.read() << 8) | (is.read() << 16) | 0xff000000;
        }
        index-=w;
      }
    break;
case ARGB:
  for (int y=h - 1; y >= 0; y--) {
    for (int x=0; x < w; x++) {
      pixels[index + x]=is.read() | (is.read() << 8) | (is.read() << 16) | (is.read() << 24);
    }
    index-=w;
  }
}
}
 else {
int count=w * h;
switch (format) {
case ALPHA:
for (int i=0; i < count; i++) {
  pixels[i]=is.read();
}
break;
case RGB:
for (int i=0; i < count; i++) {
pixels[i]=is.read() | (is.read() << 8) | (is.read() << 16) | 0xff000000;
}
break;
case ARGB:
for (int i=0; i < count; i++) {
pixels[i]=is.read() | (is.read() << 8) | (is.read() << 16) | (is.read() << 24);
}
break;
}
}
}
 else {
int index=0;
while (index < pixels.length) {
int num=is.read();
boolean isRLE=(num & 0x80) != 0;
if (isRLE) {
num-=127;
int pixel=0;
switch (format) {
case ALPHA:
pixel=is.read();
break;
case RGB:
pixel=0xFF000000 | is.read() | (is.read() << 8) | (is.read() << 16);
break;
case ARGB:
pixel=is.read() | (is.read() << 8) | (is.read() << 16) | (is.read() << 24);
break;
}
for (int i=0; i < num; i++) {
pixels[index++]=pixel;
if (index == pixels.length) break;
}
}
 else {
num+=1;
switch (format) {
case ALPHA:
for (int i=0; i < num; i++) {
pixels[index++]=is.read();
}
break;
case RGB:
for (int i=0; i < num; i++) {
pixels[index++]=0xFF000000 | is.read() | (is.read() << 8) | (is.read() << 16);
}
break;
case ARGB:
for (int i=0; i < num; i++) {
pixels[index++]=is.read() | (is.read() << 8) | (is.read() << 16) | (is.read() << 24);
}
break;
}
}
}
if (!reversed) {
int[] temp=new int[w];
for (int y=0; y < h / 2; y++) {
int z=(h - 1) - y;
System.arraycopy(pixels,y * w,temp,0,w);
System.arraycopy(pixels,z * w,pixels,y * w,w);
System.arraycopy(temp,0,pixels,z * w,w);
}
}
}
int type=(format == RGB) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
BufferedImage image=new BufferedImage(w,h,type);
WritableRaster wr=image.getRaster();
wr.setDataElements(0,0,w,h,pixels);
return image;
}
  finally {
is.close();
}
}
