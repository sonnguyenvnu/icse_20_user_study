@Override public String errorFor(List<E> values){
  for (  E value : values) {
    String error=module.errorFor(value);
    if (error != null) {
      return error;
    }
  }
  return null;
}
