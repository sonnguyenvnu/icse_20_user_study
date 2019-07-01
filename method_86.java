private BufferedImage _XXXXX_(final PcxHeader pcxHeader,final InputStream is,final ByteSource byteSource) throws ImageReadException, IOException {
  final int xSize=pcxHeader.xMax - pcxHeader.xMin + 1;
  if (xSize < 0) {
    throw new ImageReadException("Image width is negative");
  }
  final int ySize=pcxHeader.yMax - pcxHeader.yMin + 1;
  if (ySize < 0) {
    throw new ImageReadException("Image height is negative");
  }
  if (pcxHeader.nPlanes <= 0 || 4 < pcxHeader.nPlanes) {
    throw new ImageReadException("Unsupported/invalid image with " + pcxHeader.nPlanes + " planes");
  }
  final RleReader rleReader;
  if (pcxHeader.encoding == PcxHeader.ENCODING_UNCOMPRESSED) {
    rleReader=new RleReader(false);
  }
 else   if (pcxHeader.encoding == PcxHeader.ENCODING_RLE) {
    rleReader=new RleReader(true);
  }
 else {
    throw new ImageReadException("Unsupported/invalid image encoding " + pcxHeader.encoding);
  }
  final int scanlineLength=pcxHeader.bytesPerLine * pcxHeader.nPlanes;
  final byte[] scanline=new byte[scanlineLength];
  if ((pcxHeader.bitsPerPixel == 1 || pcxHeader.bitsPerPixel == 2 || pcxHeader.bitsPerPixel == 4 || pcxHeader.bitsPerPixel == 8) && pcxHeader.nPlanes == 1) {
    final int bytesPerImageRow=(xSize * pcxHeader.bitsPerPixel + 7) / 8;
    final byte[] image=new byte[ySize * bytesPerImageRow];
    for (int y=0; y < ySize; y++) {
      rleReader.read(is,scanline);
      System.arraycopy(scanline,0,image,y * bytesPerImageRow,bytesPerImageRow);
    }
    final DataBufferByte dataBuffer=new DataBufferByte(image,image.length);
    int[] palette;
    if (pcxHeader.bitsPerPixel == 1) {
      palette=new int[]{0x000000,0xffffff};
    }
 else     if (pcxHeader.bitsPerPixel == 8) {
      palette=read256ColorPalette(is);
      if (palette == null) {
        palette=read256ColorPaletteFromEndOfFile(byteSource);
      }
      if (palette == null) {
        throw new ImageReadException("No 256 color palette found in image that needs it");
      }
    }
 else {
      palette=pcxHeader.colormap;
    }
    WritableRaster raster;
    if (pcxHeader.bitsPerPixel == 8) {
      raster=Raster.createInterleavedRaster(dataBuffer,xSize,ySize,bytesPerImageRow,1,new int[]{0},null);
    }
 else {
      raster=Raster.createPackedRaster(dataBuffer,xSize,ySize,pcxHeader.bitsPerPixel,null);
    }
    final IndexColorModel colorModel=new IndexColorModel(pcxHeader.bitsPerPixel,1 << pcxHeader.bitsPerPixel,palette,0,false,-1,DataBuffer.TYPE_BYTE);
    return new BufferedImage(colorModel,raster,colorModel.isAlphaPremultiplied(),new Properties());
  }
 else   if (pcxHeader.bitsPerPixel == 1 && 2 <= pcxHeader.nPlanes && pcxHeader.nPlanes <= 4) {
    final IndexColorModel colorModel=new IndexColorModel(pcxHeader.nPlanes,1 << pcxHeader.nPlanes,pcxHeader.colormap,0,false,-1,DataBuffer.TYPE_BYTE);
    final BufferedImage image=new BufferedImage(xSize,ySize,BufferedImage.TYPE_BYTE_BINARY,colorModel);
    final byte[] unpacked=new byte[xSize];
    for (int y=0; y < ySize; y++) {
      rleReader.read(is,scanline);
      int nextByte=0;
      Arrays.fill(unpacked,(byte)0);
      for (int plane=0; plane < pcxHeader.nPlanes; plane++) {
        for (int i=0; i < pcxHeader.bytesPerLine; i++) {
          final int b=0xff & scanline[nextByte++];
          for (int j=0; j < 8 && 8 * i + j < unpacked.length; j++) {
            unpacked[8 * i + j]|=(byte)(((b >> (7 - j)) & 0x1) << plane);
          }
        }
      }
      image.getRaster().setDataElements(0,y,xSize,1,unpacked);
    }
    return image;
  }
 else   if (pcxHeader.bitsPerPixel == 8 && pcxHeader.nPlanes == 3) {
    final byte[][] image=new byte[3][];
    image[0]=new byte[xSize * ySize];
    image[1]=new byte[xSize * ySize];
    image[2]=new byte[xSize * ySize];
    for (int y=0; y < ySize; y++) {
      rleReader.read(is,scanline);
      System.arraycopy(scanline,0,image[0],y * xSize,xSize);
      System.arraycopy(scanline,pcxHeader.bytesPerLine,image[1],y * xSize,xSize);
      System.arraycopy(scanline,2 * pcxHeader.bytesPerLine,image[2],y * xSize,xSize);
    }
    final DataBufferByte dataBuffer=new DataBufferByte(image,image[0].length);
    final WritableRaster raster=Raster.createBandedRaster(dataBuffer,xSize,ySize,xSize,new int[]{0,1,2},new int[]{0,0,0},null);
    final ColorModel colorModel=new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),false,false,Transparency.OPAQUE,DataBuffer.TYPE_BYTE);
    return new BufferedImage(colorModel,raster,colorModel.isAlphaPremultiplied(),new Properties());
  }
 else   if ((pcxHeader.bitsPerPixel == 24 && pcxHeader.nPlanes == 1) || (pcxHeader.bitsPerPixel == 32 && pcxHeader.nPlanes == 1)) {
    final int rowLength=3 * xSize;
    final byte[] image=new byte[rowLength * ySize];
    for (int y=0; y < ySize; y++) {
      rleReader.read(is,scanline);
      if (pcxHeader.bitsPerPixel == 24) {
        System.arraycopy(scanline,0,image,y * rowLength,rowLength);
      }
 else {
        for (int x=0; x < xSize; x++) {
          image[y * rowLength + 3 * x]=scanline[4 * x];
          image[y * rowLength + 3 * x + 1]=scanline[4 * x + 1];
          image[y * rowLength + 3 * x + 2]=scanline[4 * x + 2];
        }
      }
    }
    final DataBufferByte dataBuffer=new DataBufferByte(image,image.length);
    final WritableRaster raster=Raster.createInterleavedRaster(dataBuffer,xSize,ySize,rowLength,3,new int[]{2,1,0},null);
    final ColorModel colorModel=new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),false,false,Transparency.OPAQUE,DataBuffer.TYPE_BYTE);
    return new BufferedImage(colorModel,raster,colorModel.isAlphaPremultiplied(),new Properties());
  }
 else {
    throw new ImageReadException("Invalid/unsupported image with bitsPerPixel " + pcxHeader.bitsPerPixel + " and planes "+ pcxHeader.nPlanes);
  }
}