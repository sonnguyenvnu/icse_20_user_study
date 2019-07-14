/** 
 * This utility method creates a texture for the provided image, and adds it to the metadata cache of the image.
 * @param img the image to have a texture metadata associated to it
 */
protected Texture addTexture(PImage img){
  Texture.Parameters params=new Texture.Parameters(ARGB,textureSampling,getHint(ENABLE_TEXTURE_MIPMAPS),textureWrap);
  return addTexture(img,params);
}
