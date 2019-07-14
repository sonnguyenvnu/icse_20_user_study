private ResponseHandler createResponseHandler(final String target,final String key,final TextContainer textContainer){
  try {
    Method method=Moco.class.getMethod(target,String.class,Resource.class);
    return (ResponseHandler)method.invoke(null,key,getResource(textContainer));
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
