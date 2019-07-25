@Override public String executable(){
  return config().executable().or("dalvikvm");
}
