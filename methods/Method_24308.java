protected boolean hasTexture(){
  if (family == GROUP) {
    return textures != null && 0 < textures.size();
  }
 else {
    return image != null;
  }
}
