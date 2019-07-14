protected boolean hasStrokedTexture(){
  if (family == GROUP) {
    return strokedTexture;
  }
 else {
    return image != null && stroke;
  }
}
