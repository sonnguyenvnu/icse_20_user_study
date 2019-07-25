@Override protected boolean supports(Class<?> clazz,byte[] content){
  return BRACKET == content[0];
}
