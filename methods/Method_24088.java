@Override public PGL beginPGL(){
  flush();
  pgl.beginGL();
  return pgl;
}
