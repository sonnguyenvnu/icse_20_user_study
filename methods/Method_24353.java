@Override public void setTint(int index,int tint){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setTint()");
    return;
  }
  if (image != null) {
    inGeo.colors[index]=PGL.javaToNativeARGB(tint);
    markForTessellation();
  }
}
