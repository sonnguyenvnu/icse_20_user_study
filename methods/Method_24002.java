protected String[] loadFragmentShader(URL url){
  try {
    return PApplet.loadStrings(url.openStream());
  }
 catch (  IOException e) {
    PGraphics.showException("Cannot load fragment shader " + url.getFile());
  }
  return null;
}
