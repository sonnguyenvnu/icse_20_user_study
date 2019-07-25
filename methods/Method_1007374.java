public static NoClassDefFoundError fail(ClassNotFoundException e){
  return new NoClassDefFoundError(e.getMessage());
}
