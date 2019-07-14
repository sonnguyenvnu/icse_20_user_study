protected PImage wrapTexture(Texture tex){
  PImage img=new PImage();
  img.parent=parent;
  img.width=img.pixelWidth=tex.width;
  img.height=img.pixelHeight=tex.height;
  img.format=ARGB;
  setCache(img,tex);
  return img;
}
