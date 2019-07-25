public static IMoocJSONResult build(Integer status,String msg,Object data){
  return new IMoocJSONResult(status,msg,data);
}
