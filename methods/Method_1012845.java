public static <T>String success(T data,String message){
  return toString(new ResultBody().setData(data).setMessage(message));
}
