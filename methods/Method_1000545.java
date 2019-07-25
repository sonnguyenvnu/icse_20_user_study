/** 
 * ???RGB???????Image,??CMYK???????
 */
public static BufferedImage redraw(BufferedImage img,Color bg){
  BufferedImage rgbImage=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
  Graphics2D g2d=rgbImage.createGraphics();
  g2d.drawImage(img,0,0,bg,null);
  g2d.dispose();
  return rgbImage;
}
