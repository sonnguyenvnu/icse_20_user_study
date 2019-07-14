@Override protected Class<?> findClass(String name) throws ClassNotFoundException {
  if (name.startsWith("com.google.errorprone.") || name.startsWith("org.checkerframework.dataflow.")) {
    return Class.forName(name);
  }
 else {
    throw new ClassNotFoundException(name);
  }
}
