public void photo(File imagefile,String transition) throws IOException {
  BufferedImage image=ImageIO.read(imagefile);
  photo(image,transition);
}
