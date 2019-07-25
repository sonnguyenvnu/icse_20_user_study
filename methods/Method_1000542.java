/** 
 * ?????????
 * @param image ??
 * @param degree ????, 90 ???????? -90 ???????
 * @return ????????
 */
public static BufferedImage rotate(BufferedImage image,int degree){
  int iw=image.getWidth();
  int ih=image.getHeight();
  int w=0;
  int h=0;
  int x=0;
  int y=0;
  degree=degree % 360;
  if (degree < 0)   degree=360 + degree;
  double ang=degree * 0.0174532925;
  if (degree == 180 || degree == 0 || degree == 360) {
    w=iw;
    h=ih;
  }
 else   if (degree == 90 || degree == 270) {
    w=ih;
    h=iw;
  }
 else {
    int d=iw + ih;
    w=(int)(d * Math.abs(Math.cos(ang)));
    h=(int)(d * Math.abs(Math.sin(ang)));
  }
  x=(w / 2) - (iw / 2);
  y=(h / 2) - (ih / 2);
  BufferedImage rotatedImage=new BufferedImage(w,h,image.getType());
  Graphics2D gs=rotatedImage.createGraphics();
  gs.fillRect(0,0,w,h);
  AffineTransform at=new AffineTransform();
  at.rotate(ang,w / 2,h / 2);
  at.translate(x,y);
  AffineTransformOp op=new AffineTransformOp(at,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
  op.filter(image,rotatedImage);
  image=rotatedImage;
  return image;
}
