/** 
 * @param shaderSource a string containing the shader's code
 */
protected boolean compileFragmentShader(){
  pgl.shaderSource(glFragment,PApplet.join(fragmentShaderSource,"\n"));
  pgl.compileShader(glFragment);
  pgl.getShaderiv(glFragment,PGL.COMPILE_STATUS,intBuffer);
  boolean compiled=intBuffer.get(0) == 0 ? false : true;
  if (!compiled) {
    PGraphics.showException("Cannot compile fragment shader:\n" + pgl.getShaderInfoLog(glFragment));
    return false;
  }
 else {
    return true;
  }
}
