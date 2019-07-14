@Override public void setTextureUV(int index,float u,float v){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setTextureUV()");
    return;
  }
  if (textureMode == IMAGE && image != null) {
    u/=image.width;
    v/=image.height;
  }
  inGeo.texcoords[2 * index + 0]=u;
  inGeo.texcoords[2 * index + 1]=v;
  markForTessellation();
}
