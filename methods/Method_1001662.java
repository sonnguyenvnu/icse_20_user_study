private static HtmlCommand build(final Matcher2 m,final ImgValign valign,final int vspace){
  if (m.find() == false) {
    return new Text("(SYNTAX ERROR)");
  }
  final String src=m.group(1);
  try {
    final File f=FileSystem.getInstance().getFile(src);
    if (f.exists() == false) {
      if (src.startsWith("http:") || src.startsWith("https:")) {
        final BufferedImage read=FileUtils.ImageIO_read(new URL(src));
        if (read == null) {
          return new Text("(Cannot decode: " + src + ")");
        }
        return new Img(new TileImage(read,valign,vspace));
      }
      return new Text("(File not found: " + f + ")");
    }
    if (f.getName().endsWith(".svg")) {
      return new Img(new TileImageSvg(f));
    }
    final BufferedImage read=FileUtils.ImageIO_read(f);
    if (read == null) {
      return new Text("(Cannot decode: " + f + ")");
    }
    return new Img(new TileImage(FileUtils.ImageIO_read(f),valign,vspace));
  }
 catch (  IOException e) {
    return new Text("ERROR " + e.toString());
  }
}
