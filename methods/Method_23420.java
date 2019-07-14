private ImageWriter imageioWriter(String extension){
  Iterator<ImageWriter> iter=ImageIO.getImageWritersByFormatName(extension);
  if (iter.hasNext()) {
    return iter.next();
  }
  return null;
}
