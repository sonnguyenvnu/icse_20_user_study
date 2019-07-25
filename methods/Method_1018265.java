public Object run() throws Exception {
  Class<?> mainClass=Thread.currentThread().getContextClassLoader().loadClass(this.mainClassName);
  Method mainMethod=mainClass.getDeclaredMethod("main",String[].class);
  return mainMethod.invoke(null,new Object[]{this.args});
}
