public String converter(Object obj){
  if (obj instanceof String) {
    return (String)obj;
  }
  String text;
  String callback=ThreadLocalUtils.getAndRemove("jsonp-callback");
  if (obj instanceof ResponseMessage) {
    ResponseMessage message=(ResponseMessage)obj;
    text=JSON.toJSONString(obj,parseFilter(message),features);
  }
 else {
    text=JSON.toJSONString(obj,features);
  }
  if (!StringUtils.isNullOrEmpty(callback)) {
    text=new StringBuilder().append(callback).append("(").append(text).append(")").toString();
  }
  return text;
}
