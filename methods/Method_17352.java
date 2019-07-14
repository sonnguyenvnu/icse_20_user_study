private ClassLoader getManagerClassLoader(ClassLoader classLoader){
  return (classLoader == null) ? getDefaultClassLoader() : classLoader;
}
