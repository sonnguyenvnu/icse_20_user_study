public static Result success(Object object){
  Result result=new Result();
  result.setCode(0);
  result.setMsg("??");
  result.setData(object);
  return result;
}
