/** 
 * Expects x1, y1, x2, y2 coordinates where (x2 >= x1) and (y2 >= y1). If tint() has been called, the image will be colored. <p/> The default implementation draws an image as a textured quad. The (u, v) coordinates are in image space (they're ints, after all..)
 */
protected void imageImpl(PImage img,float x1,float y1,float x2,float y2,int u1,int v1,int u2,int v2){
  boolean savedStroke=stroke;
  int savedTextureMode=textureMode;
  stroke=false;
  textureMode=IMAGE;
  u1*=img.pixelDensity;
  u2*=img.pixelDensity;
  v1*=img.pixelDensity;
  v2*=img.pixelDensity;
  beginShape(QUADS);
  texture(img);
  vertex(x1,y1,u1,v1);
  vertex(x1,y2,u1,v2);
  vertex(x2,y2,u2,v2);
  vertex(x2,y1,u2,v1);
  endShape();
  stroke=savedStroke;
  textureMode=savedTextureMode;
}
