private static Class<?> loadClassWithNodeClassloader(final TypeNode n,final String clazzName){
  if (n.getType() != null) {
    return loadClass(n.getType().getClassLoader(),clazzName);
  }
  return null;
}
