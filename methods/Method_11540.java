public static HttpRequestBody form(Map<String,Object> params,String encoding){
  List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(params.size());
  for (  Map.Entry<String,Object> entry : params.entrySet()) {
    nameValuePairs.add(new BasicNameValuePair(entry.getKey(),String.valueOf(entry.getValue())));
  }
  try {
    return new HttpRequestBody(URLEncodedUtils.format(nameValuePairs,encoding).getBytes(encoding),ContentType.FORM,encoding);
  }
 catch (  UnsupportedEncodingException e) {
    throw new IllegalArgumentException("illegal encoding " + encoding,e);
  }
}
