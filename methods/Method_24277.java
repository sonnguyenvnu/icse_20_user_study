static protected int getShaderType(String[] source,int defaultType){
  for (int i=0; i < source.length; i++) {
    String line=source[i].trim();
    if (PApplet.match(line,colorShaderDefRegexp) != null)     return PShader.COLOR;
 else     if (PApplet.match(line,lightShaderDefRegexp) != null)     return PShader.LIGHT;
 else     if (PApplet.match(line,texShaderDefRegexp) != null)     return PShader.TEXTURE;
 else     if (PApplet.match(line,texlightShaderDefRegexp) != null)     return PShader.TEXLIGHT;
 else     if (PApplet.match(line,polyShaderDefRegexp) != null)     return PShader.POLY;
 else     if (PApplet.match(line,triShaderAttrRegexp) != null)     return PShader.POLY;
 else     if (PApplet.match(line,quadShaderAttrRegexp) != null)     return PShader.POLY;
 else     if (PApplet.match(line,pointShaderDefRegexp) != null)     return PShader.POINT;
 else     if (PApplet.match(line,lineShaderDefRegexp) != null)     return PShader.LINE;
 else     if (PApplet.match(line,pointShaderAttrRegexp) != null)     return PShader.POINT;
 else     if (PApplet.match(line,pointShaderInRegexp) != null)     return PShader.POINT;
 else     if (PApplet.match(line,lineShaderAttrRegexp) != null)     return PShader.LINE;
 else     if (PApplet.match(line,lineShaderInRegexp) != null)     return PShader.LINE;
  }
  return defaultType;
}
