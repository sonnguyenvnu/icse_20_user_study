@Override protected boolean supports(Class<?> clazz,byte[] content){
  return (MASK & (1 << content[0])) != 0;
}
