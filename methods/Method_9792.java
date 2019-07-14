private void renderImg(final PngRenderer renderer,final BufferedImage bufferedImage) throws IOException {
  try (final ByteArrayOutputStream baos=new ByteArrayOutputStream()){
    ImageIO.write(bufferedImage,"png",baos);
    final byte[] data=baos.toByteArray();
    renderer.setImage(data);
  }
 }
