/** 
 * Store data of some kind for the renderer that requires extra metadata of some kind. Usually this is a renderer-specific representation of the image data, for instance a BufferedImage with tint() settings applied for PGraphicsJava2D, or resized image data and OpenGL texture indices for PGraphicsOpenGL.
 * @param image The image to be stored
 * @param storage The metadata required by the renderer
 */
public void setCache(PImage image,Object storage){
  cacheMap.put(image,storage);
}
