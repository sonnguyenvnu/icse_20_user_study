public void size(int width,int height,String renderer){
  if (width != this.width || height != this.height || !renderer.equals(this.renderer)) {
    if (insideSettings("size",width,height,"\"" + renderer + "\"")) {
      this.width=width;
      this.height=height;
      this.renderer=renderer;
    }
  }
}
