protected void copyToTexture(int target,int format,int id,int x,int y,int w,int h,IntBuffer buffer){
  activeTexture(TEXTURE0);
  boolean enabledTex=false;
  if (!texturingIsEnabled(target)) {
    enableTexturing(target);
    enabledTex=true;
  }
  bindTexture(target,id);
  texSubImage2D(target,0,x,y,w,h,format,UNSIGNED_BYTE,buffer);
  bindTexture(target,0);
  if (enabledTex) {
    disableTexturing(target);
  }
}
