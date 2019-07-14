protected PShader getPointShader(){
  PShader shader;
  PGraphicsOpenGL ppg=getPrimaryPG();
  if (pointShader == null) {
    if (ppg.defPointShader == null) {
      String[] vertSource=pgl.loadVertexShader(defPointShaderVertURL);
      String[] fragSource=pgl.loadFragmentShader(defPointShaderFragURL);
      ppg.defPointShader=new PShader(parent,vertSource,fragSource);
    }
    shader=ppg.defPointShader;
  }
 else {
    shader=pointShader;
  }
  shader.setRenderer(this);
  shader.loadAttributes();
  shader.loadUniforms();
  return shader;
}
