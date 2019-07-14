private void ensureDefaultConstructor(){
  try {
    getClass().getConstructor();
  }
 catch (  Exception e) {
    throw new RuntimeException(getClass() + " does not have a default constructor.");
  }
}
