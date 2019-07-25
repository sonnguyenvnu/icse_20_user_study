public boolean touch(String index){
  if (!indexExists(index)) {
    Response response=execute(PUT,index,false);
    if (response.hasFailed()) {
      String msg=null;
      try {
        msg=parseContent(response.body(),"error");
      }
 catch (      Exception ex) {
      }
      if (StringUtils.hasText(msg) && !msg.contains("IndexAlreadyExistsException")) {
        throw new EsHadoopIllegalStateException(msg);
      }
    }
    return response.hasSucceeded();
  }
  return false;
}
