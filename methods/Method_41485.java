private ClassLoader findClassloader(){
  if (Thread.currentThread().getContextClassLoader() == null && getClass().getClassLoader() != null) {
    Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
  }
  return Thread.currentThread().getContextClassLoader();
}
