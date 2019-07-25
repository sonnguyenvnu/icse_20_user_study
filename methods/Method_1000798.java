public HttpEnhanceResponse clone(){
  HttpEnhanceResponse re=new HttpEnhanceResponse();
  re.statusCode=statusCode;
  re.statusText=statusText;
  re.header=new NutMap();
  if (header != null)   re.header.putAll(header);
  re.body=body;
  return re;
}
