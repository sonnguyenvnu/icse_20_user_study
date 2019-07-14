@Override protected String valueErrorFor(T value){
  if (value == null) {
    String err=super.valueErrorFor(null);
    if (err != null) {
      return err;
    }
  }
  return module.valueErrorFor(value);
}
