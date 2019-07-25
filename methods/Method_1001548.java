private static Atom build(String text,final FontConfiguration fc,URL source,double scale,Url url) throws IOException {
  final BufferedImage read=FileUtils.ImageIO_read(source);
  if (read == null) {
    return AtomText.create("(Cannot decode: " + text + ")",fc);
  }
  return new AtomImg(read,scale,url);
}
