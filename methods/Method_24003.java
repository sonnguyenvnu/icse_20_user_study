protected String[] loadVertexShader(URL url){
  try {
    return PApplet.loadStrings(url.openStream());
  }
 catch (  IOException e) {
    PGraphics.showException("Cannot load vertex shader " + url.getFile());
  }
  return null;
}
