static <T>T checkNotNull(T reference,String errorMessageTemplate,Object... errorMessageArgs){
  if (reference == null) {
    throw new NullPointerException(String.format(errorMessageTemplate,errorMessageArgs));
  }
  return reference;
}
