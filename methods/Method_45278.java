public static Resource cookieResource(final String key,final Resource resource,final CookieAttribute... options){
  return resource(id("cookie"),cookieConfigApplier(key,resource),new ResourceReader(){
    @Override public MessageContent readFor(    final Request request){
      MessageContent messageContent=resource.readFor(request);
      return content(new Cookies().encodeCookie(key,messageContent.toString(),options));
    }
  }
);
}
