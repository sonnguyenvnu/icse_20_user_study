private void ensureRequiredConstructor(){
  Constructor[] constructors=getClass().getConstructors();
  if (getBundleConstructor(constructors) == null && getDefaultConstructor(constructors) == null) {
    throw new RuntimeException(getClass() + " does not have a constructor that takes a Bundle argument or a default constructor. Controllers must have one of these in order to restore their states.");
  }
}
