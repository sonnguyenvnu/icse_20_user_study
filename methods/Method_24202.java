@Override public PShader loadShader(String fragFilename,String vertFilename){
  PShader shader=null;
  if (fragFilename == null || fragFilename.equals("")) {
    PGraphics.showWarning(MISSING_FRAGMENT_SHADER);
  }
 else   if (vertFilename == null || vertFilename.equals("")) {
    PGraphics.showWarning(MISSING_VERTEX_SHADER);
  }
 else {
    shader=new PShader(parent,vertFilename,fragFilename);
  }
  return shader;
}
