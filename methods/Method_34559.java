@Override public boolean isWriteable(Class<?> t,Type gt,Annotation[] as,MediaType mediaType){
  return HystrixStream.class.isAssignableFrom(t);
}
