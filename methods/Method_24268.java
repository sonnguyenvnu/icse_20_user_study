protected void setUniformTex(int loc,Texture tex){
  if (texUnits != null) {
    Integer unit=texUnits.get(loc);
    if (unit != null) {
      pgl.activeTexture(PGL.TEXTURE0 + unit);
      tex.bind();
    }
 else {
      throw new RuntimeException("Cannot find unit for texture " + tex);
    }
  }
}
