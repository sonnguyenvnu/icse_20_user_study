static public BufferedImage process(BufferedImage im,int minsize){
  if (minsize != Integer.MAX_VALUE) {
    return resize(im,minsize);
  }
  return im;
}
