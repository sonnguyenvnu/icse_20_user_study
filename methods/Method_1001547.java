private static Atom build(String source,final FontConfiguration fc,final byte[] data,double scale,Url url) throws IOException {
  final BufferedImage read=ImageIO.read(new ByteArrayInputStream(data));
  if (read == null) {
    return AtomText.create("(Cannot decode: " + source + ")",fc);
  }
  return new AtomImg(read,scale,url);
}
