static private boolean anythingTrue(boolean[] list){
  for (  boolean b : list) {
    if (b)     return true;
  }
  return false;
}
