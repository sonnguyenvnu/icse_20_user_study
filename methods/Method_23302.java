/** 
 * Returns the size that will be used when textFont(font) is called. When drawing with 2x pixel density, bitmap fonts in OpenGL need to be created (behind the scenes) at double the requested size. This ensures that they're shown at half on displays (so folks don't have to change their sketch code).
 */
public int getDefaultSize(){
  return size / density;
}
