/** 
 * @param shaderSource a string containing the shader's code
 */
protected boolean compileVertexShader(){
  pgl.shaderSource(glVertex,PApplet.join(vertexShaderSource,"\n"));
  pgl.compileShader(glVertex);
  pgl.getShaderiv(glVertex,PGL.COMPILE_STATUS,intBuffer);
  boolean compiled=intBuffer.get(0) == 0 ? false : true;
  if (!compiled) {
    PGraphics.showException("Cannot compile vertex shader:\n" + pgl.getShaderInfoLog(glVertex));
    return false;
  }
 else {
    return true;
  }
}
