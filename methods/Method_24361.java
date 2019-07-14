@Override public int getAmbient(int index){
  if (family != GROUP) {
    return PGL.nativeToJavaARGB(inGeo.ambient[index]);
  }
 else {
    return 0;
  }
}
