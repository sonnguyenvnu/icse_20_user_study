@Override protected String[] loadFragmentShader(String filename,int version,String versionSuffix){
  String[] fragSrc0=sketch.loadStrings(filename);
  return preprocessFragmentSource(fragSrc0,version,versionSuffix);
}
