@Override public PShader loadShader(String fragFilename){
  if (fragFilename == null || fragFilename.equals("")) {
    PGraphics.showWarning(MISSING_FRAGMENT_SHADER);
    return null;
  }
  int type=PShader.getShaderType(parent.loadStrings(fragFilename),PShader.POLY);
  PShader shader=new PShader(parent);
  shader.setType(type);
  shader.setFragmentShader(fragFilename);
  if (type == PShader.POINT) {
    String[] vertSource=pgl.loadVertexShader(defPointShaderVertURL);
    shader.setVertexShader(vertSource);
  }
 else   if (type == PShader.LINE) {
    String[] vertSource=pgl.loadVertexShader(defLineShaderVertURL);
    shader.setVertexShader(vertSource);
  }
 else   if (type == PShader.TEXLIGHT) {
    String[] vertSource=pgl.loadVertexShader(defTexlightShaderVertURL);
    shader.setVertexShader(vertSource);
  }
 else   if (type == PShader.LIGHT) {
    String[] vertSource=pgl.loadVertexShader(defLightShaderVertURL);
    shader.setVertexShader(vertSource);
  }
 else   if (type == PShader.TEXTURE) {
    String[] vertSource=pgl.loadVertexShader(defTextureShaderVertURL);
    shader.setVertexShader(vertSource);
  }
 else   if (type == PShader.COLOR) {
    String[] vertSource=pgl.loadVertexShader(defColorShaderVertURL);
    shader.setVertexShader(vertSource);
  }
 else {
    String[] vertSource=pgl.loadVertexShader(defTextureShaderVertURL);
    shader.setVertexShader(vertSource);
  }
  return shader;
}
