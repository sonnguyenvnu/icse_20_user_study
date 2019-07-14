/** 
 * Encodes a 32-bit key frame.
 * @param tmp The output stream. Must be set to Big-Endian.
 * @param data The image data.
 * @param offset The offset to the first pixel in the data array.
 * @param width The width of the image in data elements.
 * @param scanlineStride The number to add to offset to get to the next scanline.
 */
public void writeKey32(OutputStream out,int[] data,int width,int height,int offset,int scanlineStride) throws IOException {
  tmpSeek.reset();
  long headerPos=tmpSeek.getStreamPosition();
  tmp.writeInt(0);
  tmp.writeShort(0x0000);
  int ymax=offset + height * scanlineStride;
  for (int y=offset; y < ymax; y+=scanlineStride) {
    int xy=y;
    int xymax=y + width;
    tmp.write(1);
    int literalCount=0;
    int repeatCount=0;
    for (; xy < xymax; ++xy) {
      int v=data[xy];
      for (repeatCount=0; xy < xymax && repeatCount < 127; ++xy, ++repeatCount) {
        if (data[xy] != v) {
          break;
        }
      }
      xy-=repeatCount;
      if (repeatCount < 2) {
        literalCount++;
        if (literalCount > 126) {
          tmp.write(literalCount);
          tmp.writeInts(data,xy - literalCount + 1,literalCount);
          literalCount=0;
        }
      }
 else {
        if (literalCount > 0) {
          tmp.write(literalCount);
          tmp.writeInts(data,xy - literalCount,literalCount);
          literalCount=0;
        }
        tmp.write(-repeatCount);
        tmp.writeInt(v);
        xy+=repeatCount - 1;
      }
    }
    if (literalCount > 0) {
      tmp.write(literalCount);
      tmp.writeInts(data,xy - literalCount,literalCount);
      literalCount=0;
    }
    tmp.write(-1);
  }
  long pos=tmpSeek.getStreamPosition();
  tmpSeek.seek(headerPos);
  tmp.writeInt((int)(pos - headerPos));
  tmpSeek.seek(pos);
  tmpSeek.toOutputStream(out);
}
