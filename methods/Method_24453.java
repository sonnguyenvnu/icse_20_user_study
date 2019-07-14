public void usingRepeat(boolean repeat){
  if (repeat) {
    glWrapS=PGL.REPEAT;
    glWrapT=PGL.REPEAT;
    usingRepeat=true;
  }
 else {
    glWrapS=PGL.CLAMP_TO_EDGE;
    glWrapT=PGL.CLAMP_TO_EDGE;
    usingRepeat=false;
  }
  bind();
  pgl.texParameteri(glTarget,PGL.TEXTURE_WRAP_S,glWrapS);
  pgl.texParameteri(glTarget,PGL.TEXTURE_WRAP_T,glWrapT);
  unbind();
}
