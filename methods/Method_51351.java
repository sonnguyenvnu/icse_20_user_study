private String typeErrorFor(T value){
  if (value != null && !type().isAssignableFrom(value.getClass())) {
    return value + " is not an instance of " + type();
  }
  return null;
}
