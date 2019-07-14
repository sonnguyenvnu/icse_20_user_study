protected boolean hasTexture(PImage tex){
  if (family == GROUP) {
    return textures != null && textures.contains(tex);
  }
 else {
    return image == tex;
  }
}
