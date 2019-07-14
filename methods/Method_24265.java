/** 
 * Returns the ID location of the attribute parameter given its name.
 * @param name String
 * @return int
 */
protected int getAttributeLoc(String name){
  init();
  return pgl.getAttribLocation(glProgram,name);
}
