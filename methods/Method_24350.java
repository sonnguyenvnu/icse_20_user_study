@Override public void setFill(int index,int fill){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setFill()");
    return;
  }
  if (image == null) {
    inGeo.colors[index]=PGL.javaToNativeARGB(fill);
    markForTessellation();
  }
}
