protected boolean checkPolyType(int type){
  if (getType() == PShader.POLY)   return true;
  if (getType() != type) {
    if (type == TEXLIGHT) {
      PGraphics.showWarning(PGraphicsOpenGL.NO_TEXLIGHT_SHADER_ERROR);
    }
 else     if (type == LIGHT) {
      PGraphics.showWarning(PGraphicsOpenGL.NO_LIGHT_SHADER_ERROR);
    }
 else     if (type == TEXTURE) {
      PGraphics.showWarning(PGraphicsOpenGL.NO_TEXTURE_SHADER_ERROR);
    }
 else     if (type == COLOR) {
      PGraphics.showWarning(PGraphicsOpenGL.NO_COLOR_SHADER_ERROR);
    }
    return false;
  }
  return true;
}
