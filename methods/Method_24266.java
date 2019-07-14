/** 
 * Returns the ID location of the uniform parameter given its name.
 * @param name String
 * @return int
 */
protected int getUniformLoc(String name){
  init();
  return pgl.getUniformLocation(glProgram,name);
}
