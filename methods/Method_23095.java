/** 
 * Encodes a 24-bit delta frame.
 * @param tmp The output stream. Must be set to Big-Endian.
 * @param data The image data.
 * @param prev The image data of the previous frame.
 * @param offset The offset to the first pixel in the data array.
 * @param width The width of the image in data elements.
 * @param scanlineStride The number to add to offset to get to the next scanline.
 */
public void writeDelta24(OutputStream out,int[] data,int[] prev,int width,int height,int offset,int scanlineStride) throws IOException {
  tmpSeek.reset();
  int ymin;
  int ymax=offset + height * scanlineStride;
  scanline:   for (ymin=offset; ymin < ymax; ymin+=scanlineStride) {
    int xy=ymin;
    int xymax=ymin + width;
    for (; xy < xymax; ++xy) {
      if (data[xy] != prev[xy]) {
        break scanline;
      }
    }
  }
  if (ymin == ymax) {
    tmp.writeInt(4);
    tmpSeek.toOutputStream(out);
    return;
  }
  scanline:   for (; ymax > ymin; ymax-=scanlineStride) {
    int xy=ymax - scanlineStride;
    int xymax=ymax - scanlineStride + width;
    for (; xy < xymax; ++xy) {
      if (data[xy] != prev[xy]) {
        break scanline;
      }
    }
  }
  long headerPos=tmpSeek.getStreamPosition();
  tmp.writeInt(0);
  if (ymin == offset && ymax == offset + height * scanlineStride) {
    tmp.writeShort(0x0000);
  }
 else {
    tmp.writeShort(0x0008);
    tmp.writeShort(ymin / scanlineStride);
    tmp.writeShort(0);
    tmp.writeShort((ymax - ymin + 1) / scanlineStride);
    tmp.writeShort(0);
  }
  for (int y=ymin; y < ymax; y+=scanlineStride) {
    int xy=y;
    int xymax=y + width;
    int skipCount=0;
    for (; xy < xymax; ++xy, ++skipCount) {
      if (data[xy] != prev[xy]) {
        break;
      }
    }
    if (skipCount == width) {
      tmp.write(1);
      tmp.write(-1);
      continue;
    }
    tmp.write(Math.min(255,skipCount + 1));
    if (skipCount > 254) {
      skipCount-=254;
      while (skipCount > 254) {
        tmp.write(0);
        tmp.write(255);
        skipCount-=254;
      }
      tmp.write(0);
      tmp.write(skipCount + 1);
    }
    int literalCount=0;
    int repeatCount=0;
    for (; xy < xymax; ++xy) {
      for (skipCount=0; xy < xymax; ++xy, ++skipCount) {
        if (data[xy] != prev[xy]) {
          break;
        }
      }
      xy-=skipCount;
      int v=data[xy];
      for (repeatCount=0; xy < xymax && repeatCount < 127; ++xy, ++repeatCount) {
        if (data[xy] != v) {
          break;
        }
      }
      xy-=repeatCount;
      if (skipCount < 1 && xy + skipCount < xymax && repeatCount < 2) {
        literalCount++;
        if (literalCount == 127) {
          tmp.write(literalCount);
          tmp.writeInts24(data,xy - literalCount + 1,literalCount);
          literalCount=0;
        }
      }
 else {
        if (literalCount > 0) {
          tmp.write(literalCount);
          tmp.writeInts24(data,xy - literalCount,literalCount);
          literalCount=0;
        }
        if (xy + skipCount == xymax) {
          xy+=skipCount - 1;
        }
 else         if (skipCount >= repeatCount) {
          while (skipCount > 254) {
            tmp.write(0);
            tmp.write(255);
            xy+=254;
            skipCount-=254;
          }
          tmp.write(0);
          tmp.write(skipCount + 1);
          xy+=skipCount - 1;
        }
 else {
          tmp.write(-repeatCount);
          tmp.writeInt24(v);
          xy+=repeatCount - 1;
        }
      }
    }
    if (literalCount > 0) {
      tmp.write(literalCount);
      tmp.writeInts24(data,xy - literalCount,literalCount);
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
