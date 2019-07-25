@Override public Object create() throws CoreException {
  try {
    return new E4Handler<>(Class.forName(clazz));
  }
 catch (  ClassNotFoundException e) {
    throw new CoreException(new Status(1,Activator.PLUGIN_ID,e.getMessage(),e));
  }
}
