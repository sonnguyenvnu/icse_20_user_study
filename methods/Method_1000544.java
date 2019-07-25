/** 
 * ???????????
 * @param img ????
 * @return ????
 */
public static BufferedImage read(Object img){
  try {
    if (img instanceof BufferedImage) {
      return (BufferedImage)img;
    }
    if (img instanceof CharSequence) {
      return ImageIO.read(Files.checkFile(img.toString()));
    }
    if (img instanceof File)     return ImageIO.read((File)img);
    if (img instanceof byte[])     return ImageIO.read(new ByteArrayInputStream((byte[])img));
    if (img instanceof URL)     img=((URL)img).openStream();
    if (img instanceof InputStream) {
      File tmp=File.createTempFile("nutz_img",".jpg");
      Files.write(tmp,img);
      try {
        return read(tmp);
      }
  finally {
        tmp.delete();
      }
    }
    throw Lang.makeThrow("Unkown img info!! --> " + img);
  }
 catch (  IOException e) {
    try {
      InputStream in=null;
      if (img instanceof File)       in=new FileInputStream((File)img);
 else       if (img instanceof URL)       in=((URL)img).openStream();
 else       if (img instanceof InputStream)       in=(InputStream)img;
      if (in != null)       return readJpeg(in);
    }
 catch (    IOException e2) {
      e2.fillInStackTrace();
    }
    return null;
  }
}
