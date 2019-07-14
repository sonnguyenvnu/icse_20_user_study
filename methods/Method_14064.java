public static String serializeImage(RenderedImage image) throws IOException {
  ByteArrayOutputStream output=new ByteArrayOutputStream(4096);
  ImageIO.write(image,"png",output);
  output.close();
  String encoded=Base64.encodeBase64String(output.toByteArray());
  String url="data:image/png;base64," + encoded;
  return url;
}
