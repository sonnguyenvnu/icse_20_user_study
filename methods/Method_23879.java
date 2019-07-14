/** 
 * Handle renderer-specific image drawing.
 */
@Override protected void imageImpl(PImage who,float x1,float y1,float x2,float y2,int u1,int v1,int u2,int v2){
  if (who.width <= 0 || who.height <= 0)   return;
  ImageCache cash=(ImageCache)getCache(who);
  if (cash != null) {
    if (who.pixelWidth != cash.image.getWidth() || who.pixelHeight != cash.image.getHeight()) {
      cash=null;
    }
  }
  if (cash == null) {
    cash=new ImageCache();
    setCache(who,cash);
    who.updatePixels();
    who.setModified();
  }
  if ((tint && !cash.tinted) || (tint && (cash.tintedColor != tintColor)) || (!tint && cash.tinted)) {
    who.updatePixels();
  }
  if (who.isModified()) {
    if (who.pixels == null) {
      who.pixels=new int[who.pixelWidth * who.pixelHeight];
    }
    cash.update(who,tint,tintColor);
    who.setModified(false);
  }
  u1*=who.pixelDensity;
  v1*=who.pixelDensity;
  u2*=who.pixelDensity;
  v2*=who.pixelDensity;
  context.drawImage(((ImageCache)getCache(who)).image,u1,v1,u2 - u1,v2 - v1,x1,y1,x2 - x1,y2 - y1);
}
