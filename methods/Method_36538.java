protected void pushThreadContextClassLoader(ClassLoader newContextClassLoader){
  if (newContextClassLoader != null) {
    Thread.currentThread().setContextClassLoader(newContextClassLoader);
  }
}
