private static boolean exists(String className){
  return Classpath.getSystemClassIfExists(className).isPresent();
}
