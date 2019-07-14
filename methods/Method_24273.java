protected boolean compile(){
  boolean vertRes=true;
  if (hasVertexShader()) {
    vertRes=compileVertexShader();
  }
 else {
    PGraphics.showException("Doesn't have a vertex shader");
  }
  boolean fragRes=true;
  if (hasFragmentShader()) {
    fragRes=compileFragmentShader();
  }
 else {
    PGraphics.showException("Doesn't have a fragment shader");
  }
  return vertRes && fragRes;
}
