private ResponseHandler createCookieResponseHandler(final String target,final String key,final CookieContainer cookieContainer){
  try {
    Method method=Moco.class.getMethod(target,String.class,Resource.class,CookieAttribute[].class);
    return (ResponseHandler)method.invoke(null,key,getResource(cookieContainer),cookieContainer.getOptions());
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
