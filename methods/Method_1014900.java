public static ResEntity error(int code,String msg){
  ResEntity r=new ResEntity();
  r.put("code",code);
  r.put("msg",msg);
  return r;
}
