private static List<BufferedImage> _XXXXX_(final ByteSource byteSource) throws ImageReadException, IOException {
  final ImageParser imageParser=getImageParser(byteSource);
  return imageParser.getAllBufferedImages(byteSource);
}