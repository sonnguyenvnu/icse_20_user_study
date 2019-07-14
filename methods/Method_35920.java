public static ResponseDefinition notConfigured(){
  final ResponseDefinition response=new ResponseDefinition(HTTP_NOT_FOUND,(byte[])null);
  response.wasConfigured=false;
  return response;
}
