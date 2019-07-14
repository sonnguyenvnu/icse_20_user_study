protected boolean textureIsBound(int target,int id){
  if (boundTextures == null)   return false;
  if (target == TEXTURE_2D) {
    return boundTextures[activeTexUnit][0] == id;
  }
 else   if (target == TEXTURE_RECTANGLE) {
    return boundTextures[activeTexUnit][1] == id;
  }
 else {
    return false;
  }
}
