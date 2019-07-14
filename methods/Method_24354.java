@Override public int getStroke(int index){
  if (family != GROUP) {
    return PGL.nativeToJavaARGB(inGeo.strokeColors[index]);
  }
 else {
    return 0;
  }
}
