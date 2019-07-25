private BufferedImage smaller(BufferedImage im){
  if (im == null) {
    return null;
  }
  final int nb=1;
  return im.getSubimage(nb,nb,im.getWidth() - 2 * nb,im.getHeight() - 2 * nb);
}
