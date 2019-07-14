/** 
 * Sets the size of the image and texture to width x height, and the parameters of the texture to params. If the texture is already initialized, it first destroys the current OpenGL texture object and then creates a new one with the specified size.
 * @param width int
 * @param height int
 * @param params GLTextureParameters
 */
public void init(int width,int height,Parameters params){
  setParameters(params);
  setSize(width,height);
  allocate();
}
