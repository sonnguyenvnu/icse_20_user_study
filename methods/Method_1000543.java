/** 
 * ??????????
 * @param im ????
 * @param w ??
 * @param h ??
 * @return ???????
 */
public static BufferedImage scale(BufferedImage im,int w,int h){
  int oW=im.getWidth();
  int oH=im.getHeight();
  int nW=w, nH=h;
  if (h == -1) {
    nH=(int)((float)w / oW * oH);
  }
 else   if (w == -1) {
    nW=(int)((float)h / oH * oW);
  }
  BufferedImage re=new BufferedImage(nW,nH,im.getType());
  Graphics2D gc=re.createGraphics();
  gc.drawImage(im,0,0,nW,nH,null);
  gc.dispose();
  return re;
}
