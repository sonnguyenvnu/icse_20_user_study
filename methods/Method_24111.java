@Override public void noClip(){
  if (clip) {
    flush();
    pgl.disable(PGL.SCISSOR_TEST);
    clip=false;
  }
}
