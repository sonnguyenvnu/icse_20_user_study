public void endwhile(LinkRendering nextLinkRenderer,Display out){
  this.endInlinkRendering=nextLinkRenderer;
  this.out=out;
  if (out == null) {
    throw new IllegalArgumentException();
  }
  this.testCalled=true;
}
