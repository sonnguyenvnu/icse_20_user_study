@Override public void setStroke(int index,int stroke){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setStroke()");
    return;
  }
  inGeo.strokeColors[index]=PGL.javaToNativeARGB(stroke);
  markForTessellation();
}
