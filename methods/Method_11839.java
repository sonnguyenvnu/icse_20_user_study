private void validateParameterSupplier(Class<? extends ParameterSupplier> supplierClass,List<Throwable> errors){
  Constructor<?>[] constructors=supplierClass.getConstructors();
  if (constructors.length != 1) {
    errors.add(new Error("ParameterSupplier " + supplierClass.getName() + " must have only one constructor (either empty or taking only a TestClass)"));
  }
 else {
    Class<?>[] paramTypes=constructors[0].getParameterTypes();
    if (!(paramTypes.length == 0) && !paramTypes[0].equals(TestClass.class)) {
      errors.add(new Error("ParameterSupplier " + supplierClass.getName() + " constructor must take either nothing or a single TestClass instance"));
    }
  }
}
