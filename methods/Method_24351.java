@Override public int getTint(int index){
  if (family != GROUP && image != null) {
    return PGL.nativeToJavaARGB(inGeo.colors[index]);
  }
 else {
    return 0;
  }
}
