private int getPaintTexture(){
  if (paintTexture == 0) {
    paintTexture=Texture.generateTexture(size);
  }
  return paintTexture;
}
