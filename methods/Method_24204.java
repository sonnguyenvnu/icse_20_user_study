protected PShader getPolyShader(boolean lit,boolean tex){
  PShader shader;
  PGraphicsOpenGL ppg=getPrimaryPG();
  boolean useDefault=polyShader == null;
  if (polyShader != null) {
    polyShader.setRenderer(this);
    polyShader.loadAttributes();
    polyShader.loadUniforms();
  }
  if (lit) {
    if (tex) {
      if (useDefault || !polyShader.checkPolyType(PShader.TEXLIGHT)) {
        if (ppg.defTexlightShader == null) {
          String[] vertSource=pgl.loadVertexShader(defTexlightShaderVertURL);
          String[] fragSource=pgl.loadFragmentShader(defTexlightShaderFragURL);
          ppg.defTexlightShader=new PShader(parent,vertSource,fragSource);
        }
        shader=ppg.defTexlightShader;
      }
 else {
        shader=polyShader;
      }
    }
 else {
      if (useDefault || !polyShader.checkPolyType(PShader.LIGHT)) {
        if (ppg.defLightShader == null) {
          String[] vertSource=pgl.loadVertexShader(defLightShaderVertURL);
          String[] fragSource=pgl.loadFragmentShader(defLightShaderFragURL);
          ppg.defLightShader=new PShader(parent,vertSource,fragSource);
        }
        shader=ppg.defLightShader;
      }
 else {
        shader=polyShader;
      }
    }
  }
 else {
    if (polyShader != null && polyShader.accessLightAttribs()) {
      PGraphics.showWarning(SHADER_NEED_LIGHT_ATTRIBS);
      useDefault=true;
    }
    if (tex) {
      if (useDefault || !polyShader.checkPolyType(PShader.TEXTURE)) {
        if (ppg.defTextureShader == null) {
          String[] vertSource=pgl.loadVertexShader(defTextureShaderVertURL);
          String[] fragSource=pgl.loadFragmentShader(defTextureShaderFragURL);
          ppg.defTextureShader=new PShader(parent,vertSource,fragSource);
        }
        shader=ppg.defTextureShader;
      }
 else {
        shader=polyShader;
      }
    }
 else {
      if (useDefault || !polyShader.checkPolyType(PShader.COLOR)) {
        if (ppg.defColorShader == null) {
          String[] vertSource=pgl.loadVertexShader(defColorShaderVertURL);
          String[] fragSource=pgl.loadFragmentShader(defColorShaderFragURL);
          ppg.defColorShader=new PShader(parent,vertSource,fragSource);
        }
        shader=ppg.defColorShader;
      }
 else {
        shader=polyShader;
      }
    }
  }
  if (shader != polyShader) {
    shader.setRenderer(this);
    shader.loadAttributes();
    shader.loadUniforms();
  }
  return shader;
}
