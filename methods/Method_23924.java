public boolean addTexture(PGraphicsOpenGL pg){
  int w, h;
  boolean resize;
  w=maxSize;
  if (-1 < lastTex && textures[lastTex].glHeight < maxSize) {
    h=PApplet.min(2 * textures[lastTex].glHeight,maxSize);
    resize=true;
  }
 else {
    h=minSize;
    resize=false;
  }
  Texture tex;
  if (is3D) {
    tex=new Texture(pg,w,h,new Texture.Parameters(ARGB,Texture.BILINEAR,false));
  }
 else {
    tex=new Texture(pg,w,h,new Texture.Parameters(ARGB,Texture.LINEAR,false));
  }
  if (textures == null) {
    textures=new Texture[1];
    textures[0]=tex;
    images=new PImage[1];
    images[0]=pg.wrapTexture(tex);
    lastTex=0;
  }
 else   if (resize) {
    Texture tex0=textures[lastTex];
    tex.put(tex0);
    textures[lastTex]=tex;
    pg.setCache(images[lastTex],tex);
    images[lastTex].width=tex.width;
    images[lastTex].height=tex.height;
  }
 else {
    lastTex=textures.length;
    Texture[] tempTex=new Texture[lastTex + 1];
    PApplet.arrayCopy(textures,tempTex,textures.length);
    tempTex[lastTex]=tex;
    textures=tempTex;
    PImage[] tempImg=new PImage[textures.length];
    PApplet.arrayCopy(images,tempImg,images.length);
    tempImg[lastTex]=pg.wrapTexture(tex);
    images=tempImg;
  }
  tex.bind();
  return resize;
}
