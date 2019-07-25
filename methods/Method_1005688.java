RegisterSpec spec(){
  if (spec == null) {
    code.initializeLocals();
    if (spec == null) {
      throw new AssertionError();
    }
  }
  return spec;
}
