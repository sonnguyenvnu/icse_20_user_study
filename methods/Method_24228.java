@Override protected String[] loadVertexShader(URL url,int version,String versionSuffix){
  try {
    String[] vertSrc0=PApplet.loadStrings(url.openStream());
    return preprocessVertexSource(vertSrc0,version,versionSuffix);
  }
 catch (  IOException e) {
    PGraphics.showException("Cannot load vertex shader " + url.getFile());
  }
  return null;
}
