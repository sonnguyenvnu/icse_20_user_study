private static String getModule(String moduleJava){
  return "org.lwjgl".equals(moduleJava) ? "core" : moduleJava.substring("org.lwjgl.".length());
}
