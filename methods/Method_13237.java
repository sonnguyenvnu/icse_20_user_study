protected static ImageIcon mergeIcons(ImageIcon background,ImageIcon overlay,int x,int y){
  int w=background.getIconWidth();
  int h=background.getIconHeight();
  BufferedImage image=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
  if (x + overlay.getIconWidth() > w)   x=w - overlay.getIconWidth();
  if (y + overlay.getIconHeight() > h)   y=h - overlay.getIconHeight();
  Graphics2D g2=image.createGraphics();
  g2.drawImage(background.getImage(),0,0,null);
  g2.drawImage(overlay.getImage(),x,y,null);
  g2.dispose();
  return new ImageIcon(image);
}
