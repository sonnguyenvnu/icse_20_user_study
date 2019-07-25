private boolean exclude(String name){
  if (name.startsWith("java")) {
    return true;
  }
  if (name.startsWith("org.springframework")) {
    return true;
  }
  if (name.indexOf("$$EnhancerBySpringCGLIB$$") >= 0) {
    return true;
  }
  if (name.indexOf("$$FastClassBySpringCGLIB$$") >= 0) {
    return true;
  }
  return false;
}
