private static Object getPathList(ClassLoader baseDexClassLoader) throws Exception {
  return Reflector.with(baseDexClassLoader).field("pathList").get();
}
